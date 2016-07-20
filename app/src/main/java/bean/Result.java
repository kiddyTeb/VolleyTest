package bean;


import java.util.List;

/**
 * Created by asus on 2016/7/20.
 */
public class Result {

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    private Today today;

    public List<FutureBean> getFuture() {
        return future;
    }

    public void setFuture(List<FutureBean> future) {
        this.future = future;
    }

    private List<FutureBean> future ;
}
