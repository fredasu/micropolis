// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import micropolisj.engine.*;
import static micropolisj.gui.MainWindow.formatFunds;
import static micropolisj.gui.MainWindow.formatGameDate;

public class GameOverDialog extends JDialog
{
	Micropolis engine;

	JLabel roadFundRequest = new JLabel();
	JLabel roadFundAlloc = new JLabel();
	JSlider roadFundEntry;

	JLabel policeFundRequest = new JLabel();
	JLabel policeFundAlloc = new JLabel();
	JSlider policeFundEntry;

	JLabel fireFundRequest = new JLabel();
	JLabel fireFundAlloc = new JLabel();
	JSlider fireFundEntry;

	JLabel taxRevenueLbl = new JLabel();

	static ResourceBundle strings = MainWindow.strings;

	public GameOverDialog(Window owner, Micropolis engine)
	{
		super(owner);
		setTitle(strings.getString("gameOverDlg.title"));

		this.engine = engine;

		Box mainBox = new Box(BoxLayout.Y_AXIS);
		mainBox.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
		add(mainBox, BorderLayout.CENTER);

		mainBox.add(makeMessagePane());

		JPanel buttonPane = new JPanel();
		add(buttonPane, BorderLayout.SOUTH);

		JButton continueBtn = new JButton(strings.getString("gameOverDlg.continue"));
		continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				onContinueClicked();
			}});
		buttonPane.add(continueBtn);
		
		setAutoRequestFocus_compat(false);
		pack();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		getRootPane().registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}},
			KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
			JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private void setAutoRequestFocus_compat(boolean v)
	{
		try
		{
			if (super.getClass().getMethod("setAutoRequestFocus", boolean.class) != null) {
				super.setAutoRequestFocus(v);
			}
		}
		catch (NoSuchMethodException e) {
			// ok to ignore
		}
	}

	private void onContinueClicked()
	{
		dispose();
	}

	private JComponent makeMessagePane()
	{
		JPanel balancePane = new JPanel(new GridBagLayout());
		balancePane.setBorder(BorderFactory.createEmptyBorder(8,24,8,24));

		GridBagConstraints c0 = new GridBagConstraints();

		c0.anchor = GridBagConstraints.WEST;
		c0.weightx = 0.5;
		c0.gridx = 0;
		c0.gridy = 0;

		JLabel thLbl = new JLabel(strings.getString("gameOverDlg.msg"));
		Font origFont = thLbl.getFont();

		Font headFont = origFont.deriveFont(Font.BOLD | Font.ITALIC, 50);
		thLbl.setFont(headFont);
		thLbl.setForeground(Color.RED);
		balancePane.add(thLbl, c0);

		return balancePane;
	}
}
