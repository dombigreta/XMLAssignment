public class Book {

    private int Id;
    private String Title;
    private String Description;
    private int AmountAvailable;
    private int price;

    public Book() {
    }

    public Book(int id, String title, String description, int amountAvailable, int price) {
        Id = id;
        Title = title;
        Description = description;
        AmountAvailable = amountAvailable;
        this.price = price;
    }

    public int getId()
    {
        return  Id;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getAmountAvailable() {
        return AmountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        AmountAvailable = amountAvailable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
