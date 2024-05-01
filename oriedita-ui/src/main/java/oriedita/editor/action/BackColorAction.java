package oriedita.editor.action;

import com.formdev.flatlaf.FlatLaf;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oriedita.editor.Colors;
import oriedita.editor.FrameProvider;
import oriedita.editor.databinding.FoldedFigureModel;
import oriedita.editor.swing.CustomColorChooserPanel;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@ApplicationScoped
@ActionHandler(ActionType.backColorAction)
public class BackColorAction extends AbstractOrieditaAction {
    @Inject
    FrameProvider frameProvider;
    @Inject
    FoldedFigureModel foldedFigureModel;

    @Inject
    public BackColorAction() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //以下にやりたいことを書く
        Color backColor = showCustomColorDialog(frameProvider, "B_col", FlatLaf.isLafDark() ? Colors.FIGURE_BACK_DARK : Colors.FIGURE_BACK);

        if (backColor != null) {
            foldedFigureModel.setBackColor(backColor);
        }
    }

    private Color showCustomColorDialog(FrameProvider frameProvider, String title, Color initialColor){
        JColorChooser colorChooser = new JColorChooser();
        colorChooser.addChooserPanel(new CustomColorChooserPanel());

        final boolean[] isOK = new boolean[1];

        if(initialColor != null){
            colorChooser.setColor(initialColor);
        }
        ActionListener okListener = e -> isOK[0] = true;

        ActionListener cancelListener = e -> isOK[0] = false;

        JDialog dialog = JColorChooser.createDialog(frameProvider.get(), title, true, colorChooser, okListener, cancelListener);
        dialog.setVisible(true);

        return isOK[0] ? colorChooser.getColor() : initialColor;
    }
}
