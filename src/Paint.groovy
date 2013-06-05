def cli = new CliBuilder(usage: 'groovy Paint -f[hv] filename')
cli.h(longOpt: 'help', 'usage information')
cli.f(argName: 'filename', longOpt: 'filename', args: 1, required: true, 'the file to parse')
cli.v(argName: 'verbose', longOpt: 'verbose', 'Verbose output')

def options = cli.parse(args)

if (options.h) {
    cli.usage(); return
}

def verbose = options.v

def int totalColours = 0;
def customers = []

def file = new File(options.f)

file.eachLine { line, lineNumber ->

    if (lineNumber == 1) {
        totalColours = line as int; return
    }

//    if (!line.contains('M')) if (verbose) {
//        println "Skipping line (${line}) as there are no Mattes "; return
//    }

    def lineMap = line.split(' ')
    if (lineMap.size() % 2 != 0) {
        println "Error parsing input file. Please check that format is correct"; System.exit(1)
    }

    def likes = (1..lineMap.size() / 2).collectEntries { index ->

        def key = index + (index - 2) as int
        def value = index + index - 1 as int

        if (key > totalColours) {
            println "Error parsing input file. Customer at ${line} wants a colour we do not supply"; System.exit(1)
        }

        [lineMap[key], lineMap[value]]
    }

    customers.add(new Customer(likes.sort { item -> item.value }))
}

if (verbose) {
    println "- The shopkeeper has to create ${totalColours} batches of either matte or gloss"
    println "- We have ${customers.size()} customers"
    println "- Each customer has a list of batches they like."
    println "- Now we can sort each of these so that the 'fussy' (only one like) ones are processed first and the "
    println "- reasonable ones are processed later."
}


def sortedCustomers = customers.sort { item -> item.likes.size() }

if (verbose) {
    println "In addition, we will keep the matte selections till last.."
}

def preparedBatch = [:]
customers.each {
    customer ->

        if (verbose) {
            println "Processing Customer --> ${customer}"
        }

        for (it in customer.likes) {

            if (it.value == preparedBatch.get(it.key as int)) {break}

            if (preparedBatch.get(it.key as int) != null) {
                if (customer.isFussy()) {
                    println "No solution exists"; System.exit(1)
                }
                break
            }
            //Add a batch
            preparedBatch.get(it.key as int, it.value)
            break
        }
}



//Let's expand the preparedBatch to list of all batches
(1..totalColours).each { colour ->
    if (!preparedBatch.containsKey(colour)) preparedBatch.put(colour, 'G')
}
println "${preparedBatch.sort { item -> item.key }.values()}"


