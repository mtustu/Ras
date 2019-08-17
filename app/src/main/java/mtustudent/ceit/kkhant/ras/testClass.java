package mtustudent.ceit.kkhant.ras;
public class testClass {
    private String foodName,price,image,username,estTime;

    public testClass()
    {

    }
    public testClass(String foodName,String price,String image,String username,String estTime)
    {
        this.foodName=foodName;
        this.price=price;
        this.image=image;
        this.username = username;
        this.estTime=estTime;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }
    public String getEstTime() {
        return estTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public void setEstTime(String estTime)
    {
        this.estTime = estTime;
    }
}