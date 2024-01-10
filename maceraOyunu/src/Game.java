import java.util.Scanner;

public class Game {
    public Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera oyununa Hoşgeldin !");
        System.out.println("Lütfen isminizi giriniz : ");
        //String playerName = input.nextLine();
        Player player = new Player("yiğit");
        System.out.println("Bu karanlık ve sisli adaya hoşgeldin " + player.getName() + " Adada hayatta kalmak,buradan kurtulmaktan daha da zor...");
        System.out.println("Maceraya devam etmek için karakterini seçmelisin!. !!Unutma!! Karakterin gücü ve sağlığı seni başarıya götürür.");
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("******Bölgeler******");
            System.out.println();
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Tüccar Dükkanı ");
            System.out.println("******Tehlikeli Bölgeler******");
            System.out.println("3- Mağara");
            System.out.println("4- Orman");
            System.out.println("5- Nehir");
            System.out.print("Lütfen gitmek istediğin bölgeyi seç : ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }
            if (!location.onLocation()) {
                System.out.println("Çok ağır bir darbe aldın ve vücudun artık kaldıramıyor gözlerin kapanıyor...");
                System.out.println("---Öldün--- \n!!OYUN BİTTİ!!");
                break;
            }

        }

    }

}
