package at.htl.entity;

public class CartItem {

    int _pizzaId;
    String _text;
    double _price;
    int _amount;

    public CartItem(int _pizzaId, String _text, double _price, int _amount) {
        this._pizzaId = _pizzaId;
        this._text = _text;
        this._price = _price;
        this._amount = _amount;
    }

    public CartItem() {
    }

    public int get_pizzaId() {
        return _pizzaId;
    }

    public void set_pizzaId(int _pizzaId) {
        this._pizzaId = _pizzaId;
    }

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "_pizzaId=" + _pizzaId +
                ", _text='" + _text + '\'' +
                ", _price=" + _price +
                ", _amount=" + _amount +
                '}';
    }
}
