package formularios;

/**
 *
 * @author Carlos Escobar - ES21001
 */
public class MenuAction {

    protected boolean isCancel() {
        return cancel;
    }

    public void cancel() {
        this.cancel = true;
    }

    private boolean cancel = false;
}
