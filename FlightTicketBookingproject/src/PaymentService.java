// Payment Processing with Stripe Integration

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.exception.StripeException;
import com.stripe.param.ChargeCreateParams;

public class PaymentService {
    static {
        Stripe.apiKey = "sk_test_51PqYDSFk1QIjA4QEV7yWpOYRWuAht5Tw5carvpGkRqKl7us5qyYF6MR5kt33P2yc36i1XPxv0gcvo5raWzvWHFom00wHV9X9pV";  // Replace with your Stripe secret key
    }

    public boolean processPayment(int bookingId, double amount, String currency, String source) {
        if (bookingId <= 0 || amount <= 0 || currency == null || source == null || currency.isEmpty() || source.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount((long) (amount * 100)) // Amount in cents
                .setCurrency(currency)
                .setSource(source)
                .setDescription("Payment for booking ID " + bookingId)
                .build();

            Charge charge = Charge.create(params);
            return charge.getPaid();
        } catch (StripeException e) {
            e.printStackTrace();
            return false;
        }
    }
}
