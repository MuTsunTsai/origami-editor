package oriedita.editor.action;

import java.awt.event.ActionEvent;

public class MattakuhidoiOrieditaAction extends AbstractOrieditaAction implements OrieditaAction{
    private final Runnable actionMethod;
    private final ActionType actionType;

    public MattakuhidoiOrieditaAction(ActionType actionType, Runnable actionMethod){
        this.actionType = actionType;
        this.actionMethod = actionMethod;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionMethod.run();
    }

    @Override
    public ActionType getActionType(){
        return actionType;
    }
}
