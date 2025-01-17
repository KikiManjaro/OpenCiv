package me.rhin.openciv.shared.packet.type;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import me.rhin.openciv.shared.packet.Packet;

public class SetUnitOwnerPacket extends Packet {

	int tileGridX, tileGridY;
	private int unitID;
	private String playerOwner;
	private String prevPlayerOwner;

	@Override
	public void write(Json json) {
		super.write(json);
		json.writeValue("tileGridX", tileGridX);
		json.writeValue("tileGridY", tileGridY);
		json.writeValue("unitID", unitID);
		json.writeValue("playerOwner", playerOwner);
		json.writeValue("prevPlayerOwner", prevPlayerOwner);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		this.tileGridX = jsonData.getInt("tileGridX");
		this.tileGridY = jsonData.getInt("tileGridY");
		this.unitID = jsonData.getInt("unitID");
		this.playerOwner = jsonData.getString("playerOwner");
		this.prevPlayerOwner = jsonData.getString("prevPlayerOwner");
	}

	public void setUnit(String playerOwner, String prevPlayerOwner, int unitID, int tileGridX, int tileGridY) {
		this.tileGridX = tileGridX;
		this.tileGridY = tileGridY;
		this.unitID = unitID;
		this.playerOwner = playerOwner;
		this.prevPlayerOwner = prevPlayerOwner;
	}

	public int getTileGridX() {
		return tileGridX;
	}

	public int getTileGridY() {
		return tileGridY;
	}

	public int getUnitID() {
		return unitID;
	}

	public String getPlayerOwner() {
		return playerOwner;
	}
	
	public String getPrevPlayerOwner() {
		return prevPlayerOwner;
	}
}
