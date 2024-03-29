import java.util.Random;

public class BattleLoc extends Location{

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;



    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız :" + this.getName());
        System.out.println("Çok dikkatli ol sesler duyuyorum burada anladığıma göre " + obsNumber + " tane " + this.getObstacle().getName() + " olabilir.");
        System.out.println("Savaşmak için s ' ye bas. Kaçmak için k ' ye bas.");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)){
            System.out.println("Mücadele başladı");
            if (combat(obsNumber)) {

                System.out.println(this.getName() + " tüm düşmanları öldürdünüz!");
                return true;

            }

        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Güçlü bir darbe aldıktan sonra dizlerinin üstüne çöküyorsun ve tüm hayatın gözlerinin önünde akıp bitiyor...");
            System.out.println("Oyunu Kaybettin Savaşçı....!");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber){

        for (int i = 1; i <= obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print(" <V>ur veya <K>aç : ");
                String selectCombat = input.nextLine();
                selectCombat = selectCombat.toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Vuruş yaptınız !");
                    this.obstacle.setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println("Canavar vuruş yaptı!!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                }else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Bir düşmanı yendiniz!");
                System.out.println(this.getObstacle().getAward() + " Altın kazandınız!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel Altın : " + this.getPlayer().getMoney());

            }else {
                return false;
            }
        }
        return true;
    }


    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+ " Canı : " + this.getObstacle().getHealth());
        System.out.println();
    }



    public void obstacleStats(int i){
        System.out.println(i + " . " +  this.getObstacle().getName() + " Değerleri");
        System.out.println("----------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());


    }


    public void playerStats(){
        System.out.println("Oyuncu değerleri");
        System.out.println("----------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Kuşanılan silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Altın : " + this.getPlayer().getMoney());





    }


    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
