package handler;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import data.DataManager;
import main.Main;

public class RandomBox {
	DataManager data = Main.data;
	FileConfiguration file = data.getFile();
	public void makeRandomBox(String name, Player p) {
		if(file.contains("randomBox."+name)) { // 이미 존재할 때
			p.sendMessage(ChatColor.RED + "이미 존재하는 랜덤박스 입니다.");
			return;
		}
		file.set("randomBox."+name, name);
		data.saveConfig();
		p.sendMessage(ChatColor.GREEN + "랜덤박스 항목이 생성되었습니다.");
	}
	public void deleteRandomBox(String name, Player p) {
		if(!file.contains("randomBox."+name)) { // 존재하지 않는 랜덤 박스일 때
			p.sendMessage(ChatColor.RED + "존재하지 않는 랜덤박스 입니다.");
			return;
		}
		file.set("randomBox."+name, null);
		data.saveConfig();
		p.sendMessage(ChatColor.GREEN + "성공적으로 제거되었습니다.");
	}
	public void makeRandomBoxItem(String name, Player p) {
		if(!file.contains("randomBox."+name)) { // 존재하지 않는 랜덤 박스일 때
			p.sendMessage(ChatColor.RED + "존재하지 않는 랜덤박스 입니다.");
			return;
		}
		ItemStack item = p.getInventory().getItemInMainHand();
		if(item == null || item.getType() == Material.AIR) {
			 p.sendMessage(ChatColor.RED + "손에 아무것도 들고 있지 않은 상태로는 수행할 수 없습니다.");
			 return;
		}
		file.set("randomBox."+name+".item", item);
		data.saveConfig();
		p.sendMessage(ChatColor.GREEN + "성공적으로 등록되었습니다.");
	}
	public void listRandomBox(Player p) {
		p.sendMessage(ChatColor.YELLOW + "------------------------");
		p.sendMessage(ChatColor.AQUA + "   <현재 랜덤박스 목록>   ");
		p.sendMessage(ChatColor.YELLOW + "------------------------");
		for (String key : data.getFile().getConfigurationSection("randomBox").getKeys(false)) {
			p.sendMessage(ChatColor.GREEN + key);
		}
		p.sendMessage(ChatColor.YELLOW + "------------------------");
	}
	public void printRandomBox(Player p) {
		p.sendMessage(ChatColor.YELLOW + "--------------------------------------------");
		p.sendMessage(ChatColor.AQUA + "         <랜덤 박스 명령어>         ");
		p.sendMessage(ChatColor.YELLOW + "--------------------------------------------");
		p.sendMessage(ChatColor.GOLD + "/랜덤박스 생성 [이름]" + ChatColor.WHITE + "- 새 항목의 랜덤박스를 생성함.");
		p.sendMessage(ChatColor.GOLD + "/랜덤박스 목록" + ChatColor.WHITE + "- 랜덤박스의 목록을 나열함.");
		p.sendMessage(ChatColor.GOLD + "/랜덤박스 삭제 [이름]" + ChatColor.WHITE + "- 해당하는 랜덤박스를 삭제함.");
		p.sendMessage(ChatColor.GOLD + "/랜덤박스 지정 [이름]" + ChatColor.WHITE + "- 손에 들고 있는 아이템을 해당 항목의 랜덤박스 아이템으로 등록함.");
		p.sendMessage(ChatColor.YELLOW + "--------------------------------------------");
	}
	public void giveMeItem(Player p) {
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("테스트 입니다");
		meta.setLore(lore);
		item.setItemMeta(meta);
		p.getInventory().addItem(item);
	}
}
