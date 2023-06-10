public class FacebookDBTester{
	public static void main(String[] args) {
      FacebookDB fbDb = new FacebookDB();

      //fbDb.createDatabase();
      
      //Part A
      String sql = "INSERT INTO user VALUES ('bart.simpson@lyit.ie', 'LetMeIn','Bart','Simpson') " +
                   "ON DUPLICATE KEY UPDATE password='LetMeIn', firstname='Bart', lastname='Simpson'";
      fbDb.insertIntoDatabase(sql); 
      
      //Part C
      String password= fbDb.getUserPasswordFromDatabase("bart.simpson@lyit.ie");
      
      // Should display "LetMeIn"
      System.out.println("Password is " + password);
      
       
	}
}