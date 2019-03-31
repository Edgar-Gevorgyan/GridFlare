package epl.students.programmers.gridflare.tools;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_Room")
public class Room implements Parcelable {
    @DatabaseField(generatedId = true, unique = true)
    int idRoom;
    @DatabaseField(canBeNull = false)
    String room_name;
    @DatabaseField
    int floor;
    @DatabaseField(canBeNull=false, foreign = true, foreignColumnName = "idPlace", foreignAutoCreate = true)
    private Place place_idPlace;

    public Room() {
    }

    public Room(String room_name, int floor) {
        this.room_name = room_name;
        this.floor = floor;
    }

    public Room(String room_name, int floor, Place place) {
        this.room_name = room_name;
        this.floor = floor;
        this.place_idPlace = place;
    }

    protected Room(Parcel in) {
        idRoom = in.readInt();
        room_name = in.readString();
        place_idPlace = in.readParcelable(Place.class.getClassLoader());
        floor = in.readInt();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public String getRoom_name() {
        return room_name;
    }

    public int getFloor() {
        return floor;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Place getIdPlace(){
        return this.place_idPlace;
    }

    @Override
    public String toString() {
        return " room : "+ getRoom_name() + " floor : " + getFloor();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idRoom);
        parcel.writeParcelable(place_idPlace, i);
        parcel.writeString(room_name);
        parcel.writeInt(floor);
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Room) {
            Room tmp = (Room) o;
            return tmp.getRoom_name().equals(this.getRoom_name());
        }
        return false;
    }
}