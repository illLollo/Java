dellpc: public class Main dellpc: {dellpc:     {dellpc:     public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException dellpc:         try dellpc:             final Scanner sc = new Scanner(System.in);dellpc:         {dellpc:             final Messanger c = new Messanger(95);dellpc:             dellpc:             dellpc:             System.out.println("Inserisci il tuo nome: ");dellpc:             String line = sc.nextLine();dellpc:             System.out.println("Benvenuto nella chat " + line + "!");dellpc:             dellpc:             dellpc:             c.startReciving(str -> System.out.println(str));dellpc:             while (true)dellpc:                 c.send(line + ": " + sc.nextLine(), InetAddress.getByName("192.168.1.110"));dellpc:         } dellpc:         dellpc:         catch (SocketException ex) dellpc:     }dellpc:             ex.printStackTrace();dellpc:         {dellpc:         }dellpc:         