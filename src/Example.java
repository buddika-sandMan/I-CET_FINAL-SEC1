import java.util.Scanner;

class Example{

    static int count=1;

    //Database area
    static String[][] placeOrderDB = new String[100][5];



    public static void main(String[] args) {
        appInitial();

    }

    private static void appInitial() {
        Scanner input = new Scanner(System.in);


        String[] mainMenu = {"Place Order","Search Order","View Reports","Change Order Status","Delete Order","Print All orders"};

        for(int i=0; i< mainMenu.length; i++){
            System.out.println("["+(i+1)+"] "+mainMenu[i]);
        }

        System.out.print("Select your choice : ");

        int x = input.nextInt();

        switch (x){
            case 1:
                placeOrder();
                break;
            case 2:
                searchOrder();
                break;
            case 3:
//                viewReports();
                break;
            case 4:
//                changeOrderStatus();
                break;
            case 5:
                deleteOrder();
                break;
            case 6:
                printAll();
                break;
            default:
                return;
        }
    }

    public static void placeOrder(){
        String contactNo, shirtSize, orderStatus, orderId, orderYesNo;
        int qty;
        double amount;
        boolean getContactVerification;

        Scanner input = new Scanner(System.in);

        while(true){

            orderId = genarateOrderID();
            System.out.println("Order ID : ODR#" + orderId);

            System.out.print("Enter Customer Contact Number : ");
            contactNo = input.nextLine();
            getContactVerification = validatePhoneNo(contactNo);
            if(getContactVerification==true){
                System.out.print("Enter T-Shirt Size (XS/S/M/L/XL/XXL) : ");
                shirtSize = input.nextLine();

                System.out.print("Enter QTY : ");
                qty = input.nextInt();
                input.nextLine();

                amount = calculateOrderAmount(qty, shirtSize);
                System.out.println("Amount : " + amount);

                System.out.print("Do you want to place this order? (y/n) : ");
                orderYesNo = input.nextLine();

                if(orderYesNo.equals("y")){
                    for (int i = 0; i < placeOrderDB.length; i++){
                        if (placeOrderDB[i][1] != null){
                            if (placeOrderDB[i][0].equalsIgnoreCase(contactNo)){
                                System.out.println("Order already exists!");
                                break;
                            }
                        }else{
                            placeOrderDB[i][0] = "ODR#"+orderId;
                            placeOrderDB[i][1] = contactNo;
                            placeOrderDB[i][2] = shirtSize;
                            placeOrderDB[i][3] = Integer.toString(qty);
                            placeOrderDB[i][4] = Double.toString(amount);

                            System.out.println("Order Saved!\n");
                            System.out.println("1) Do you want add another Order\n");
                            System.out.println("2) Back to menu");

                            int option = input.nextInt();

                            switch (option){
                                case 1:
                                    placeOrder();
                                    break; //customerForLoop;
                                case 2:
                                    appInitial();
                                    break;
                            }
                        }
                    }
                } else {
                    appInitial();
                }
            } else {
                System.out.println("Entered contact number duplicate or wrong, please check.");
                placeOrder();
            }
        }
    }

    public static boolean validatePhoneNo(String contactNo){

        int contactLength = contactNo.length();
        int[] contNo = new int[10];

        for (int i=0; i< placeOrderDB.length;i++) {

            if(contactNo==placeOrderDB[i][1]){
                return false;
            } else {
                if(contactLength==10){

                    for (int j=0; j<10; j++){
                        contNo[j] = Character.getNumericValue(contactNo.charAt(j));
                    }

                    if(contNo[0] == 0 & contNo[1] == 7){
                        return true;
                    }

//                    for(int j=0; j< contNo.length; j++){
//                        System.out.println(contNo[j]);
//                    }

                    return false;
                }
            }
        }

        return false;

    }

    public static String genarateOrderID(){

        if(null==placeOrderDB[0][0]) {
            return "0001";
        }

        for(int i=1; i<placeOrderDB.length; i++){

            if(i<10){
                count++;
                return "0000"+count;
            } else if(i<100){
                count++;
                return "000"+count;
            } else if(i<1000){
                count++;
                return "00"+count;
            }
            return ""+count;
        }
        return null;
    }

    public static double calculateOrderAmount(int qty, String shirtSize){

        if(shirtSize.equals("XS")){
            return 600 * qty;
        } else if (shirtSize.equals("S")){
            return 800 * qty;
        } else if(shirtSize.equals("M")){
            return 900 * qty;
        } else if(shirtSize.equals("L")){
            return 1000 * qty;
        } else if(shirtSize.equals("XL")){
            return 1100 * qty;
        } else if(shirtSize.equals("XXL")){
            return 1200 * qty;
        }
        return 0;

    }

    public static void searchOrder(){

        String contactNo;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter Search phone number : ");
        contactNo = input.nextLine();

        for (int i = 0; i < placeOrderDB.length; i++){
            if(placeOrderDB[i][0]!=null){
                if(placeOrderDB[i][1].equals(contactNo)){
                    System.out.println("Order id : " + placeOrderDB[i][0]);
                    System.out.println("Contact No : " + placeOrderDB[i][1]);
                    System.out.println("Shirt size : " + placeOrderDB[i][2]);
                    System.out.println("Quntity : " + placeOrderDB[i][3]);
                    System.out.println("Amountt : " + placeOrderDB[i][4]);
                }
            }else {
                System.out.println("Place order not found!");
                appInitial();
            }
        }
    }

    public static void printAll(){
        for (int i = 0; i < placeOrderDB.length; i++){
            if(placeOrderDB[0][0]==null) {
                System.out.println("Place order database is empty");
                appInitial();
            }else if(placeOrderDB[i][0]==null){
                appInitial();
            }else if(placeOrderDB[i][0]!=null){
                System.out.println("Order id : " + placeOrderDB[i][0]);
                System.out.println("Contact No : " + placeOrderDB[i][1]);
                System.out.println("Shirt size : " + placeOrderDB[i][2]);
                System.out.println("Quntity : " + placeOrderDB[i][3]);
                System.out.println("Amountt : " + placeOrderDB[i][4]);
                System.out.println("============================");
            }else{
                appInitial();
            }

        }
    }

    public static void deleteOrder(){

        String deleteOrder;

        Scanner input = new Scanner(System.in);

        System.out.print("Which order do you want to delete? ");
        deleteOrder = input.nextLine();

        for(int i=0; i<placeOrderDB.length; i++){
            if(placeOrderDB[i][1].equals(deleteOrder)){
                System.out.println("Order id : " + placeOrderDB[i][0]);
                System.out.println("Contact No : " + placeOrderDB[i][1]);
                System.out.println("Shirt size : " + placeOrderDB[i][2]);
                System.out.println("Quntity : " + placeOrderDB[i][3]);
                System.out.println("Amountt : " + placeOrderDB[i][4]);

                System.out.println("=========================");
                System.out.println("Confirm deletion");
                System.out.print("(1) Yes OR (2) No : ");

                int x = input.nextInt();

                switch(x){
                    case 1:
                        //deletePlaceOrderData(deleteOrder);
                        for(int j=0; j<placeOrderDB.length; j++){
                            if(placeOrderDB[j][0]==null){
                                System.out.println("Place order database is empty");
                                appInitial();
                            }else if(placeOrderDB[j][1].equals(deleteOrder)) {
                                System.out.println("Test1");
                                placeOrderDB[j][0] = null;
                                placeOrderDB[j][1] = null;
                                placeOrderDB[j][2] = null;
                                placeOrderDB[j][3] = null;
                                placeOrderDB[j][4] = null;
                                System.out.println("Delete successful");
                                appInitial();
                            }
                            System.out.println("Test2");
                        }
                        break;
                    case 2:
                        System.out.println("Canceled");
                        appInitial();
                        break;
                }
            }
        }
    }

//    public static boolean deletePlaceOrderData(String deleteOrder){
//        for(int i=0; i<placeOrderDB.length; i++){
//            if(placeOrderDB[0][0]==null){
//                return false;
//            }else if(placeOrderDB[i][1].equals(deleteOrder)) {
//                System.out.println("Test");
//                placeOrderDB[i][0] = null;
//                placeOrderDB[i][1] = null;
//                placeOrderDB[i][2] = null;
//                placeOrderDB[i][3] = null;
//                placeOrderDB[i][4] = null;
//                return true;
//            }
//        }
//        return false;
//    }
}



