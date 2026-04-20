/** Distance keeps track of the current distance the user has traveled */
public class Distance {

        int distance;

        /** Creates a distance object and sets it to 0 for the user */
        public Distance() {
            this.distance = 0;
        }
        
        /** Gets the current distance for the user
          * @return The current distance traveled
          */
        public int getDistance() {
            return distance;
        }

        /** Sets the user distance 
          * @param n the user's distance
          */ 
        public void setDistance(int n) {
            this.distance = n;
        }

        /** Increments the user's distance counter by one */
        public void addDistance() {
            this.distance += 1;
        }

}
