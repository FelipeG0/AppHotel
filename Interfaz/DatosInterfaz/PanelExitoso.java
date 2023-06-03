package DatosInterfaz;

import javax.swing.*;
import java.awt.*;

public class PanelExitoso extends JPanel {
    private JLabel label;

    public PanelExitoso() {
        label = new JLabel("Inicio exitoso");

        setLayout(new FlowLayout());
        add(label);
    }
}
