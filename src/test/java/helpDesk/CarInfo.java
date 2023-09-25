package helpDesk;

class CarInfo {
    private String company;
    private int count;

    public CarInfo(String company, int count) {
        this.company = company;
        this.count = count;
    }

    public String getCompany() {
        return company;
    }

    public int getCount() {
        return count;
    }
}