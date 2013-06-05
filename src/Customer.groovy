/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 01/06/2013
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */
class Customer {

    def likes = [:]

    public boolean isFussy(){
        return likes.size() == 1
    }

    Customer(likes) {
        this.likes = likes
    }


    @Override
    public java.lang.String toString() {
        return "Customer{" +
                "likes=" + likes +
                '}';
    }
}
