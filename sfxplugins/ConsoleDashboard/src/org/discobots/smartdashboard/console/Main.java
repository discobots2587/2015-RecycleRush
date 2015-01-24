package org.discobots.smartdashboard.console;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import dashfx.controls.bases.ControlBase;
import dashfx.lib.controls.Category;
import dashfx.lib.controls.Control;
import dashfx.lib.controls.Designable;
import dashfx.lib.data.DataCoreProvider;
import dashfx.lib.data.values.SmartValueAdapter;

@Category("General")
@Designable(value = "Console", description = "NetConsole")
public class Main extends ControlBase {

	TextArea console;
	ContextMenu rightClickMenu;
	MenuItem pauseItem;
	MenuItem clearItem;

	public Main() {
		console = new TextArea();
		console.setEditable(false);
		console.appendText("Hello... I'm your friendly console!\n");
		startListening();
		console.appendText("Listener Loaded!");

		
/*		rightClickMenu = new ContextMenu(); // create context menu
		pauseItem = new MenuItem("Pause"); // create menu items
		clearItem = new MenuItem("Clear");
		pauseItem.setOnAction(new EventHandler<ActionEvent>() {
			// event handlers for menu items
			public void handle(ActionEvent event) {
				if (paused) {
					paused = false;
					pauseItem.setText("Pause");
				} else {
					paused = true;
					pauseItem.setText("Play");
				}
			}
		});
		clearItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				cleanup = true;
			}
		});
		console.addEventHandler(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					// event handler for context menu, connects it to console
					public void handle(MouseEvent event) {

						System.out.println("clicked!");
						rightClickMenu.show(console, Side.LEFT, 0, 0);
					}
				});
*/
	}

	@Override
	public void registered(DataCoreProvider dcp) {

	}

	@Override
	public Node getUi() {
		return console;
	}

	@Override
	protected void changed(Object arg0, SmartValueAdapter arg1) {

	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////////////////////////////////////////////////////////

	Thread listener;
	Thread transferer;

	public static byte[] getPacket(DatagramSocket socket, DatagramPacket buf) {
		try {
			socket.receive(buf);
		} catch (IOException localIOException) {
			return null;
		}
		byte[] ret = new byte[buf.getLength()];
		System.arraycopy(buf.getData(), 0, ret, 0, ret.length);
		return ret;
	}

	public static DatagramSocket makeRecvSocket() {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(null);
			socket.setReuseAddress(true);
			socket.bind(new InetSocketAddress(6666));
		} catch (SocketException e) {
			e.printStackTrace();
			socket.close();
			return null;
		}
		return socket;
	}

	public static Thread startDaemonThread(Runnable r, String name) {
		Thread t = new Thread(r, name);
		t.setDaemon(true);
		t.start();
		return t;
	}

	volatile DatagramSocket socket_hook = null;
	volatile boolean discard = false;
	volatile boolean paused = false;
	volatile boolean cleanup = false;

	public void dispose() {
		stopListening();
	}

	public static String drainToString(ArrayList<byte[]> arr) {
		int netlength = 0;
		for (byte[] b : arr) {
			netlength += b.length;
		}
		byte[] sum = new byte[netlength];
		int mark = 0;
		for (int i = 0; i < arr.size(); i++) {
			byte[] b = (byte[]) arr.get(i);
			System.arraycopy(b, 0, sum, mark, b.length);
			arr.set(i, null);
			mark += b.length;
		}
		arr.clear();
		return new String(sum);
	}

	void startListening() {
		final BlockingQueue<byte[]> queue = new LinkedBlockingQueue<byte[]>();
		this.listener = startDaemonThread(new Runnable() {
			public void run() {
				DatagramSocket socket = makeRecvSocket();
				if (socket == null) {
					return;
				}
				socket_hook = socket;
				byte[] buf = new byte[4096];
				DatagramPacket datagram = new DatagramPacket(buf, buf.length);
				while (!Thread.interrupted()) {
					byte[] s = getPacket(socket, datagram);
					if ((s != null) && (!discard)) {
						try {
							queue.put(s);
						} catch (InterruptedException localInterruptedException) {
							socket.close();
							return;
						}
					}
				}
				socket.close();
			}
		}, "Console-Listener");
		this.transferer = startDaemonThread(new Runnable() {
			public void run() {
				final ArrayList<byte[]> temp = new ArrayList<byte[]>();
				while (!cleanup) {
					try {
						temp.add((byte[]) queue.take());
					} catch (InterruptedException localInterruptedException) {
						if (cleanup) {
							return;
						}
					}
					queue.drainTo(temp);
					console.appendText("> " + drainToString(temp));
				}
				console.clear();
			}
		}, "Console-Transfer");
	}

	void stopListening() {
		this.cleanup = true;
		if (this.socket_hook != null) {
			this.socket_hook.close();
		}
		this.listener.interrupt();
		this.transferer.interrupt();
	}

}
