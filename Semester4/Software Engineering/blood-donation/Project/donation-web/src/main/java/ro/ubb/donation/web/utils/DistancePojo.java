package ro.ubb.donation.web.utils;

import java.util.Arrays;

public class DistancePojo {
    private String status;

    private String[] destination_addresses;

    private String[] origin_addresses;

    private Rows[] rows;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String[] getDestination_addresses ()
    {
        return destination_addresses;
    }

    public void setDestination_addresses (String[] destination_addresses)
    {
        this.destination_addresses = destination_addresses;
    }

    public String[] getOrigin_addresses ()
    {
        return origin_addresses;
    }

    public void setOrigin_addresses (String[] origin_addresses)
    {
        this.origin_addresses = origin_addresses;
    }

    public Rows[] getRows ()
    {
        return rows;
    }

    public void setRows (Rows[] rows)
    {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "DistancePojo [status=" + status + ", destination_addresses="
                + Arrays.toString(destination_addresses)
                + ", origin_addresses=" + Arrays.toString(origin_addresses)
                + ", rows=" + Arrays.toString(rows) + "]";
    }

}
