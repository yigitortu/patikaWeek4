public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player) {
        super(player, "Güvenli ev");
    }


    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz.");
        System.out.println("Burada dinleniyorsun ve kimsecikler yok içeride yanan ateş yüzünden uyuya kalıyorsun....");
        System.out.println("Canınız yenilendi.");
        this.getPlayer().setHealth(this.getPlayer().getOrjinalHealth());
        return super.onLocation();
    }
}
