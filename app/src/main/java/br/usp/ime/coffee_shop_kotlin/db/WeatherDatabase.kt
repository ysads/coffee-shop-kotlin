package br.usp.ime.coffee_shop_kotlin.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import br.usp.ime.coffee_shop_kotlin.domain.Weather

val DATABASENAME = "weather_db"
val TABLENAME = "weather"

val COL_ID = "id"
val COL_TEMPERATURE = "temperature"
val COL_TIMESTAMP = "timestamp"
val COL_REGION = "region"
val COL_ICON = "icon"

class WeatherDatabase(var context: Context) : SQLiteOpenHelper(
    context, DATABASENAME, null,
    2
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_REGION + " VARCHAR(256)," + COL_ICON + " VARCHAR(4)," + COL_TIMESTAMP + " INTEGER," + COL_TEMPERATURE + " FLOAT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteTable = "DROP TABLE $TABLENAME"
        db?.execSQL(deleteTable)
        onCreate(db);
    }

    fun insert(weather: Weather) {
        val database = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_ICON, weather.icon)
        contentValues.put(COL_REGION, weather.region)
        contentValues.put(COL_TEMPERATURE, weather.temperature)
        contentValues.put(COL_TIMESTAMP, weather.timestamp)

        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun getFromRegion(region: String): MutableList<Weather> {
        val list: MutableList<Weather> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLENAME WHERE region = \"$region\""
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                list.add(getNext(result))
            } while (result.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    private fun getNext(result: Cursor): Weather {
        return Weather(
            icon = result.getString(result.getColumnIndex(COL_ICON)),
            region = result.getString(result.getColumnIndex(COL_REGION)),
            temperature = result.getString(result.getColumnIndex(COL_TEMPERATURE)).toFloat(),
            timestamp = result.getString(result.getColumnIndex(COL_TIMESTAMP)).toInt(),
        )
    }
}