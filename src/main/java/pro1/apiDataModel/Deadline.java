package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Deadline {
    @SerializedName("eprDeadlinePrihlaska")
    public DeadlineValue eprDeadlinePrihlaska;

    public static class DeadlineValue {
        @SerializedName("value")
        public String value;
    }

    public String getApplicationDeadline() {
        return eprDeadlinePrihlaska != null ? eprDeadlinePrihlaska.value : null;
    }

    @Override
    public String toString() {
        return "Deadline{" +
                "eprDeadlinePrihlaska=" + (eprDeadlinePrihlaska != null ? eprDeadlinePrihlaska.value : "null") +
                '}';
    }
}
