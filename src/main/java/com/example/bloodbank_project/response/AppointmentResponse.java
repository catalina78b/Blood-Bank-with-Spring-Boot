package com.example.bloodbank_project.response;

public class AppointmentResponse {
    private boolean created=false;
    private boolean canceled=false;
    private boolean confirmed=false;

    public AppointmentResponse(boolean created, boolean canceled, boolean confirmed) {
        this.created = created;
        this.canceled = canceled;
        this.confirmed = confirmed;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
