package view;

import controller.PetAdoptionController;
import model.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * GUI class for the Pet Adoption system.
 */
public class PetAdoptionView extends JFrame {
    private PetAdoptionController controller;
    private DefaultListModel<String> petListModel;
    private JList<String> petList;
    private JComboBox<String> sortBox;

    public PetAdoptionView(PetAdoptionController controller) {
        this.controller = controller;
        setTitle("Pet Adoption Center");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        petListModel = new DefaultListModel<>();
        petList = new JList<>(petListModel);
        sortBox = new JComboBox<>(new String[]{"Name", "Age", "Species"});

        JButton addBtn = new JButton("Add Pet");
        JButton adoptBtn = new JButton("Adopt");
        JButton removeBtn = new JButton("Remove");
        JButton saveBtn = new JButton("Save");

        addBtn.addActionListener(this::handleAddPet);
        adoptBtn.addActionListener(e -> controller.adoptPet(getSelectedPetId()));
        removeBtn.addActionListener(e -> controller.removePet(getSelectedPetId()));
        sortBox.addActionListener(e -> controller.sortPets((String) sortBox.getSelectedItem()));
        saveBtn.addActionListener(e -> controller.saveToJson());

        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(adoptBtn);
        panel.add(removeBtn);
        panel.add(saveBtn);
        panel.add(sortBox);

        add(new JScrollPane(petList), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    public void showPets(List<Pet> pets) {
        petListModel.clear();
        for (Pet p : pets) {
            petListModel.addElement(p.getId() + " - " + p.getName() + " - " + p.getDetails() + (p.isAdopted() ? " [ADOPTED]" : ""));
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private String getSelectedPetId() {
        String selected = petList.getSelectedValue();
        if (selected == null) return null;
        return selected.split(" - ")[0];
    }

    private void handleAddPet(ActionEvent e) {
        String name = JOptionPane.showInputDialog(this, "Enter pet name:");
        String age = JOptionPane.showInputDialog(this, "Enter pet age:");
        String species = JOptionPane.showInputDialog(this, "Enter pet species:");
        String type = JOptionPane.showInputDialog(this, "Enter pet type (dog, cat, rabbit):");
    }
}