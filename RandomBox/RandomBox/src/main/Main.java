package main;

import org.bukkit.plugin.java.JavaPlugin;

import command.CommandManager;
import data.DataManager;
import event.RandomBoxEvent;
import handler.RandomBox;

public class Main extends JavaPlugin{
	public static DataManager data;
	public static Main main;
	@Override
	public void onEnable() {
		data = new DataManager(this);
		main = this;
		getServer().getPluginManager().registerEvents(new RandomBoxEvent(), this);
		getCommand("랜덤박스").setExecutor(new CommandManager());
		/*
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Client.POSITION) {
			@Override
			public void onPacketReceiving(PacketEvent e) {
				PacketContainer packet = e.getPacket();
				Player p = e.getPlayer();
				double x = packet.getDoubles().read(0);
				double y = packet.getDoubles().read(0);
				double z = packet.getDoubles().read(0);
				boolean isOnGround = packet.getBooleans().read(0);
				
				p.sendMessage("INBOUND PACKET: x: " + x + " y " + y + " z : " + z);
				p.sendMessage("ON GROUND? " + isOnGround);
			}
		});*/
	}
	@Override
	public void onDisable() {
		
	}
}
