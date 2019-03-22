import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Order {
    private static int counter=0;

    private final LocalDate orderDate;
    private final int orderId;
    private boolean status;
    private final int quantity;
    private final Shipping shipment;
    private final String productTitle;
    private final String customerName;
    private Integer trackingNumber;

    public Order(final LocalDate orderDate,
                 final int quantity,
                 final Shipping shipment,
                 final String productTitle,
                 final String customerName) {
        this.orderDate = orderDate;
        this.status = false;
        this.quantity = quantity;
        this.shipment = shipment;
        this.productTitle = productTitle;
        this.customerName = customerName;
        this.trackingNumber = null;
        counter++;
        this.orderId = counter;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public boolean getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public Shipping getShipment() {
        return shipment;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public void shipOrder() {
        this.status = true;
    }

    public void setTrackingNumber(int trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", quantity=" + quantity +
                ", shipment=" + shipment +
                ", product=" + productTitle +
                ", customer=" + customerName +
                ", trackingNumber=" + trackingNumber +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }


    public enum Shipping {
        STANDARD (1), OVERNIGHT (2), RUSH (3);

        private static HashMap<Integer, Shipping> codeMap = new HashMap<Integer, Shipping>();
        static {
            for (Shipping s: Shipping.values()) {
                codeMap.put(s.speedCode, s);
            }
        }

        private final int speedCode;

        Shipping(int speedCode) {
            this.speedCode = speedCode;
        }

        public int getSpeedCode() {
            return this.speedCode;
        }

        public static Shipping ofCode(int speedCode) {
            return codeMap.get(speedCode);
        }
    }
}