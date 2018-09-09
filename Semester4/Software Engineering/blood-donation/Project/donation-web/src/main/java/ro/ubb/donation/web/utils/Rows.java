package ro.ubb.donation.web.utils;

import java.util.Arrays;

public class Rows {
    private Elements[] elements;

    public Elements[] getElements ()
    {
        return elements;
    }

    public void setElements (Elements[] elements)
    {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "Rows [elements=" + Arrays.toString(elements) + "]";
    }

}

