package eu.f3rog.stockticker.model;

/**
 * Class {@link Symbol}
 *
 * @author FrantisekGazo
 * @version 2016-08-04
 */
public final class Symbol {

    private final String mName;

    public Symbol(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        return mName != null ? mName.equals(symbol.mName) : symbol.mName == null;

    }

    @Override
    public int hashCode() {
        return mName != null ? mName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return mName;
    }
}
