package Model;

import javafx.util.Builder;


public class InstrumentBuilder implements Builder {
    private Instrument instrument;

    public InstrumentBuilder()
    {
        this.reset();
    }

    public void reset()
    {
        this.instrument = new Instrument();
    }
    public void setInstrumentName(String name)
    {

    }

    @Override
    public Object build() {
        return null;
    }
}
