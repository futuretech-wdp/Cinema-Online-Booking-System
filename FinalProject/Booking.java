package FinalProject;

public class Booking {
   
    private String movie;
    private String date;
    private String time;
    private String seat;
    private String customerName;
    private String contact;
    private String email;
    private String age;
    private String gender;
    private String customerType;
    private String finalPrice;
    private String discount;

 
    public Booking(String movie, String date, String time, String seat, String customerName, 
                   String contact, String email, String age, String gender, String customerType, 
                   String finalPrice, String discount) {
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.seat = seat;
        this.customerName = customerName;
        this.contact = contact;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.customerType = customerType;
        this.finalPrice = finalPrice;
        this.discount = discount;
    }

   
    public String getMovie() { 
        return movie; 
    }
    public String getDate() { 
        return date; 
    }
    public String getTime() { 
        return time; 
    }
    public String getSeat() { 
        return seat; 
    }
    public String getCustomerName() { 
        return customerName; 
    }
    public String getContact() { 
        return contact; 
    }
    public String getEmail() { 
        return email; 
    }
    public String getAge() { 
        return age; 
    }
    public String getGender() { 
        return gender; 
    }
    public String getCustomerType() { 
        return customerType; 
    }
    public String getFinalPrice() { 
        return finalPrice; 
    }
    public String getDiscount() { 
        return discount; 
    }
}