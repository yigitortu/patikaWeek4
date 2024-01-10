public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("---Mağazaya hoşgeldin evlat!---");
        System.out.println("Geri geldiğine sevindim buradan kendine yeni ekipmanlar alabilirsin savaşçı !");
        System.out.println("1 - Silahlar");
        System.out.println("2 - Zırhlar");
        System.out.println("3 - Ayrıl..");
        System.out.println("Bir seçim yap : ");
        int selectCase = input.nextInt();
        while (selectCase < 1 || selectCase > 3) {
            System.out.println("Benimle alay etmeye mi geldin, Tekrar söyle");
            selectCase = input.nextInt();
        }
        switch (selectCase) {
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor();
                break;
            case 3:
                System.out.println("Umarım hayatta kalırsın...Dikkatli ol evlat");
                return true;
        }

        return true;
    }

    public void printWeapon() {
        System.out.println("---Silahlar---");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println("ID : " + w.getId() + " - " + w.getName() +
                    " , Para : " + w.getPrice() +
                    " , Hasar : " + w.getDamage());
        }
    }

    public void buyWeapon(){
        System.out.println("Almak istediğin silahı seç :");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 1 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Benimle alay etmeye mi geldin, Tekrar söyle!!");
            selectWeaponID = input.nextInt();
        }
        Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
        if (selectedWeapon != null){
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeteri kadar altının yok Başka sefere...");
            }else {
                System.out.println(selectedWeapon.getName() + " Silahını satın aldın.");
                // Oyuncunun bakiyesi yeterli ise silahın parasını oyuncunun bakiyesinden çıkarıyoruz.
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();

                this.getPlayer().setMoney(balance);
                System.out.println("Kalan altın : " + this.getPlayer().getMoney());
                // Aldıgımız silahı direkt envantere eklemek.
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("Kuşanılan silah : " +  this.getPlayer().getInventory().getWeapon().getName());
            }
        }
    }

    public void printArmor() {
        System.out.println("Zırhlar");
        for (Armor a: Armor.armors() ){
            System.out.println("ID : " + a.getId() + " - " + a.getName() +
                    " , Para : " + a.getPrice() +
                    " , Blok gücü : " + a.getBlock());
        }
    }

    public void buyArmor(){
        System.out.println("Kendine bir zırh seç : ");
        int selectArmorID = input.nextInt();

        while (selectArmorID < 1 || selectArmorID > Armor.armors().length){
            System.out.println("Benimle alay etmeye mi geldin, Tekrar söyle!!");
            selectArmorID = input.nextInt();
        }
        Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

        if (selectedArmor != null){
            if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeteri kadar altının yok Başka sefere...");
            }else {
                System.out.println(selectedArmor.getName()+ " Zırhını satın aldınız.");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);

                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("Kuşanılan zırh : " +  this.getPlayer().getInventory().getArmor().getName());

                System.out.println("Kalan altın : " + this.getPlayer().getMoney());



            }
        }













    }
}
