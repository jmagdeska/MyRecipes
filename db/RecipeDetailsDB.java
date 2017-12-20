package com.example.jana.myrecipes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jana.myrecipes.models.RecModel;
import com.example.jana.myrecipes.models.RecipeModel;
import com.example.jana.myrecipes.models.RecipesListModel;

import java.util.ArrayList;

/**
 * Created by Kosara on 12/19/2017.
 */

public class RecipeDetailsDB extends SQLiteOpenHelper {

    private static final String TAG = RecipeDetailsDB.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyRecipes";
    private static final String RECIPE_TABLE = "RecipeDetailsDB";

    private String[] recipe1 = {"Garlic Shrimp Linguine",
            "30 min",
            "-1 pound uncooked linguine\n" +
                    "-3 cloves garlic, minced\n" +
                    "-1 tablespoon butter\n" +
                    "-1 teaspoon chopped fresh parsley\n" +
                    "-3 tablespoons white wine \n" +
                    "-1 pinch salt and pepper to taste\n" +
                    "-2 teaspoons grated Parmesan cheese\n" +
                    "-1 pound medium shrimp,peeped and deveined",
            "Bring a large pot of lightly salted water to a boil. Add pasta and cook for 8 to 10 minutes or until al dente; drain.\n" +
                    "In a medium saucepan, melt butter over medium low heat; add wine, cheese, garlic, parsley and salt and pepper to taste. Simmer over low heat for 3 to 5 minutes, stirring frequently.\n" +
                    "Increase heat to medium high and add shrimp to saucepan; cook for about 3 to 4 minutes or until shrimp begins to turn pink. Do not overcook.\n" +
                    "Divide pasta into portions and spoon sauce on top; garnish with Parmesan cheese and fresh parsley, if desired." };

    private String[] recipe2 = {"Goulash Supreme",
                                "20 min",
                                "-1 pound lean ground beef \n" +
                                        "- 1 1/2 cups macaroni \n" +
                                        "-1 quart stewed tomatoes \n" +
                                        "-2 teaspoons paprika \n" +
                                        "-1 tablespoon chili powder \n" +
                                        "-1/2 cup chopped onion \n" +
                                        "-1 clove garlic, minced \n" +
                                        "-6 ounces tomato paste (optional)",
                                "In large saucepan brown ground chuck, drain.\n" +
                                        "Add tomatoes, onions, garlic, paprika, chili powder, macaroni and tomato paste, if desired. Add water, a tablespoon at a time, if mixture seems too dry. Simmer until macaroni is tender.\n"};

    private String[] recipe3 = {"Chocolate Chip Cookies",
                                "60 min",
                                "-1 cup butter, softened \n" +
                                        "-1 cup white sugar \n" +
                                        "-1 cup packed brown sugar \n" +
                                        "-2 eggs \n" +
                                        "-2 teaspoons vanilla extract \n" +
                                        "-3 cups all-purpose flour \n" +
                                        "-1 teaspoon baking soda \n" +
                                        "-2 teaspoons hot water \n" +
                                        "-1/2 teaspoon salt \n" +
                                        "-2 cups semisweet chocolate chips \n" +
                                        "-1 cup chopped walnuts",
                                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                                        "Cream together the butter, white sugar, and brown sugar until smooth. Beat in the eggs one at a time, then stir in the vanilla. Dissolve baking soda in hot water. Add to batter along with salt. Stir in flour, chocolate chips, and nuts. Drop by large spoonfuls onto ungreased pans.\n" +
                                        "Bake for about 10 minutes in the preheated oven, or until edges are nicely browned."};
    private String[] recipe4 = {"Lasagna",
                                    "3 h 15 m",
                                    "-1 pound sweet Italian sausage \n" +
                                            "-3/4 pound lean ground beef \n" +
                                            "-1/2 cup minced onion \n" +
                                            "-2 cloves garlic, crushed \n" +
                                            "-1 (28 ounce) can crushed tomatoes \n" +
                                            "-2 (6 ounce) cans tomato paste \n" +
                                            "-2 (6.5 ounce) cans canned tomato sauce \n" +
                                            "-1/2 cup water \n" +
                                            "-2 tablespoons white sugar \n" +
                                            "-1 1/2 teaspoons dried basil leaves \n" +
                                            "-1/2 teaspoon fennel seeds \n" +
                                            "-1 teaspoon Italian seasoning \n" +
                                            "-1 tablespoon salt \n" +
                                            "-1/4 teaspoon ground black pepper \n" +
                                            "-4 tablespoons chopped fresh parsley \n" +
                                            "-12 lasagna noodles \n" +
                                            "-16 ounces ricotta cheese \n" +
                                            "-1 egg \n" +
                                            "-1/2 teaspoon salt \n" +
                                            "-3/4 pound mozzarella cheese, sliced \n" +
                                            "-3/4 cup grated Parmesan cheese",
                                    "In a Dutch oven, cook sausage, ground beef, onion, and garlic over medium heat until well browned. Stir in crushed tomatoes, tomato paste, tomato sauce, and water. Season with sugar, basil, fennel seeds, Italian seasoning, 1 tablespoon salt, pepper, and 2 tablespoons parsley. Simmer, covered, for about 1 1/2 hours, stirring occasionally.\n" +
                                            "Bring a large pot of lightly salted water to a boil. Cook lasagna noodles in boiling water for 8 to 10 minutes. Drain noodles, and rinse with cold water. In a mixing bowl, combine ricotta cheese with egg, remaining parsley, and 1/2 teaspoon salt.\n" +
                                            "Preheat oven to 375 degrees F (190 degrees C).\n" +
                                            "To assemble, spread 1 1/2 cups of meat sauce in the bottom of a 9x13 inch baking dish. Arrange 6 noodles lengthwise over meat sauce. Spread with one half of the ricotta cheese mixture. Top with a third of mozzarella cheese slices. Spoon 1 1/2 cups meat sauce over mozzarella, and sprinkle with 1/4 cup Parmesan cheese. Repeat layers, and top with remaining mozzarella and Parmesan cheese. Cover with foil: to prevent sticking, either spray foil with cooking spray, or make sure the foil does not touch the cheese.\n" +
                                            "Bake in preheated oven for 25 minutes. Remove foil, and bake an additional 25 minutes. Cool for 15 minutes before serving.\n"};

    private String[] recipe5 = {"Banana Nut Bread",
                                "2 h 20 m",
                                "-1/2 cup butter, softened \n" +
                                        "-1 cup white sugar \n" +
                                        "-2 eggs \n" +
                                        "-2 very ripe bananas, mashed \n" +
                                        "-1 cup self-rising flour \n" +
                                        "-1/4 cup flax seed meal \n" +
                                        "-1/2 cup chopped walnuts (optional)",
                                "Preheat an oven to 325 degrees F (165 degrees C). Grease a 9x5-inch loaf pan, or line a muffin pan with cupcake liners.\n" +
                                        "In a mixing bowl, beat the butter and sugar with an electric mixer until light and fluffy.\n" +
                                        "Beat in the eggs one at a time, followed by the bananas.\n" +
                                        "Mix in the self-rising flour and flax seed meal until just incorporated; fold in the walnuts.\n" +
                                        "Pour batter into the prepared pan.\n" +
                                        "Bake in the preheated oven until a toothpick inserted into the center comes out clean, about 20 minutes for muffins, or 1 hour for a loaf. Cool in the pan for 10 minutes before removing to cool completely on a wire rack.\n"};

    private String[] recipe6 = {"Thai Chicken with Basil Stir Fry",
                                "35 min",
                                "-2 cups uncooked jasmine rice \n" +
                                        "-1 quart water\n" +
                                        "-3/4 cup coconut milk \n" +
                                        "-3 tablespoons soy sauce \n" +
                                        "-3 tablespoons rice wine vinegar \n" +
                                        "-1 1/2 tablespoons fish sauce \n" +
                                        "-3/4 teaspoon red pepper flakes \n" +
                                        "-1 tablespoon olive oil \n" +
                                        "-1 medium onion, sliced \n" +
                                        "-2 tablespoons fresh ginger root, minced \n" +
                                        "-3 cloves garlic, minced \n" +
                                        "-2 pounds skinless, boneless chicken breast halves - cut into 1/2 inch strips \n" +
                                        "-3 shiitake mushrooms, sliced \n" +
                                        "-5 green onions, chopped \n" +
                                        "-1 1/2 cups chopped fresh basil leaves",
                                "Bring rice and water to a boil in a pot. Cover, reduce heat to low, and simmer 20 minutes.\n" +
                                        "In a bowl, mix the coconut milk, soy sauce, rice wine vinegar, fish sauce, and red pepper flakes.\n" +
                                        "In a skillet or wok, heat the oil over medium-high heat. Stir in the onion, ginger, and garlic, and cook until lightly browned. Mix in chicken strips, and cook about 3 minutes, until browned. Stir in the coconut milk sauce. Continue cooking until sauce is reduced be about 1/3. Mix in mushrooms, green onions, and basil, and cook until heated through. Serve over the cooked rice.\n"};
    private String[] recipe7 = {"Chicken Wild Rice Soup",
                                "2 h 25 m",
                                "-1/2 cup butter \n" +
                                        "-1 finely chopped onion \n" +
                                        "-1/2 cup chopped celery \n" +
                                        "-1/2 cup sliced carrots \n" +
                                        "-1/2 pound fresh sliced mushrooms \n" +
                                        "-3/4 cup all-purpose flour \n" +
                                        "-6 cups chicken broth \n" +
                                        "-2 cups cooked wild rice \n" +
                                        "-1 pound boneless skinless chicken breasts, cooked and cubed \n" +
                                        "-1/2 teaspoon salt \n" +
                                        "-1/2 teaspoon curry powder \n" +
                                        "-1/2 teaspoon mustard powder \n" +
                                        "-1/2 teaspoon dried parsley \n" +
                                        "-1/2 teaspoon ground black pepper \n" +
                                        "-1 cup slivered almonds \n" +
                                        "-3 tablespoons dry sherry \n" +
                                        "-2 cups half-and-half",
                                "Melt butter in a large saucepan over medium heat. Stir in the onion, celery and carrots and saute for 5 minutes. Add the mushrooms and saute for 2 more minutes. Then add the flour and stir well. Gradually pour in the chicken broth, stirring constantly, until all has been added. Bring just to a boil, reduce heat to low and let simmer.\n" +
                                        "Next, add the rice, chicken, salt, curry powder, mustard powder, parsley, ground black pepper, almonds and sherry. Allow to heat through, then pour in the half-and-half. Let simmer for 1 to 2 hours. (Note: Do not boil or your roux will break.)\n"};

    private String[] recipe8 = {"Swedish Saffron Buns",
                                "1 h 55 m",
                                "-2 1/8 cups milk \n" +
                                        "-1/2 cup butter \n" +
                                        "-3 (0.6 ounce) cakes compressed fresh yeast \n" +
                                        "-8 ounces quark or sour cream \n" +
                                        "-2 (.5 gram) packets powdered saffron \n" +
                                        "-2/3 cup sugar \n" +
                                        "-1/2 teaspoon salt \n" +
                                        "-7 1/2 cups all-purpose flour \n" +
                                        "-1 cup raisins (optional) \n" +
                                        "-1 eggs, beaten",
                                "Heat the milk and butter in a small saucepan until the butter has melted and the temperature has reached 100 degrees F (38 degrees C). Crumble the yeast into a bowl, then pour in the warm milk. Stir well until the yeast dissolves.\n" +
                                        "Stir in the quark, saffron, sugar, salt, and 7 cups of the flour. Mix the dough in the bowl until it becomes shiny and silky, adding more flour as needed until it begins to come away from the sides of the bowl. Cover, and let rise for 40 minutes.\n" +
                                        "Prepare 2 or 3 baking sheets by covering each with a sheet of parchment paper. Lightly flour a work surface, punch down the dough, then divide into 35 pieces. Roll each piece into a rope, 5 to 6 inches long. With the rope lying flat on the work surface, roll each end towards the center, in opposite directions, creating a curled S-shape. Place the buns on the prepared baking sheets, and garnish with raisins if desired. Cover with a towel, and allow to rise for an additional 30 minutes while you preheat oven to 425 degrees F (220 degrees C).\n" +
                                        "Gently brush each bun with beaten egg, then bake in the oven until puffed and golden, 5 to 10 minutes.\n"};
    private String[] recipe9 = {"Roasted Cauliflower Rice",
                                "47 m",
                                "-cooking spray \n" +
                                        "-2 heads cauliflower, cut into 1/2-inch pieces \n" +
                                        "-1 1/2 tablespoons avocado oil \n" +
                                        "-3/4 teaspoon salt, divided",
                                "Preheat oven to 450 degrees F (230 degrees C). Line 3 baking sheets with aluminum foil and lightly spray with cooking spray.\n" +
                                        "Fill a food processor 1/4 of the way with cauliflower pieces. Pulse 8 to 12 times until cauliflower is the size of rice grains. Transfer to a large bowl. Repeat with remaining cauliflower pieces.\n" +
                                        "Drizzle avocado oil over cauliflower \"rice\"; toss until well-distributed.\n" +
                                        "Spread 2 1/2 cups of the rice in an even layer on each lined baking sheet.\n" +
                                        "Roast rice until golden, about 16 minutes. Remove from oven, stir well, and spread out again. Return to oven and roast for 6 minutes. Remove from oven, stir well, and spread out again. Continue roasting until browned, about 5 minutes more.\n" +
                                        "Season each sheet of roasted rice with 1/4 teaspoon salt and place in a large container.\n"};
    private String[] recipe10 = {"Maple Salmon",
                                "1 h",
                                "-1/4 cup maple syrup \n" +
                                        "-2 tablespoons soy sauce \n" +
                                        "-1 clove garlic, minced \n" +
                                        "-1/4 teaspoon garlic salt \n" +
                                        "-1/8 teaspoon ground black pepper \n" +
                                        "-1 pound salmon",
                                "In a small bowl, mix the maple syrup, soy sauce, garlic, garlic salt, and pepper.\n" +
                                        "Place salmon in a shallow glass baking dish, and coat with the maple syrup mixture. Cover the dish, and marinate salmon in the refrigerator 30 minutes, turning once.\n" +
                                        "Preheat oven to 400 degrees F (200 degrees C).\n" +
                                        "Place the baking dish in the preheated oven, and bake salmon uncovered 20 minutes, or until easily flaked with a fork."};
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    public static final String RECIPE_ID = "_id";
    public static final String NAME = "name";
    public static final String TIME = "time";
    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String DESCRIPTION = "description";
    //

    private static final String RECIPE_DETAILS_TABLE_CREATE =
            "CREATE TABLE " + RECIPE_TABLE + " (" +
                    RECIPE_ID +  " INTEGER PRIMARY KEY, " +
                    NAME + ", " +
                    TIME + ", " +
                    INGREDIENTS  + ", " +
                    DESCRIPTION + " );";


    public RecipeDetailsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct RecipeDetailsDB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RECIPE_DETAILS_TABLE_CREATE);
        fillDatabaseWithData(db,recipe1);
        fillDatabaseWithData(db,recipe2);
        fillDatabaseWithData(db,recipe3);
        fillDatabaseWithData(db,recipe4);
        fillDatabaseWithData(db,recipe5);
        fillDatabaseWithData(db,recipe6);
        fillDatabaseWithData(db,recipe7);
        fillDatabaseWithData(db,recipe8);
        fillDatabaseWithData(db,recipe9);
        fillDatabaseWithData(db,recipe10);

    }
    public void fillDatabaseWithData(SQLiteDatabase db,String [] recipe) {
        // Create a container for the data.
        ContentValues values = new ContentValues();

        values.put(NAME, recipe[0]);
        values.put(TIME,recipe[1]);
        values.put(INGREDIENTS,recipe[2]);
        values.put(DESCRIPTION,recipe[3]);

        db.insert(RECIPE_TABLE, null, values);
    }


    //Insert RecModel
    public Long insertRecipe(String name, String time, String ingredients, String description) {
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(TIME,time);
        values.put(INGREDIENTS,ingredients);
        values.put(DESCRIPTION,description);
        try {
            if (mWritableDB == null) {mWritableDB = getWritableDatabase();}
            newId = mWritableDB.insert(RECIPE_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;
    }

    // Select RecModel
    public RecipesListModel getRecipes(){

        RecipesListModel recipesListModel = new RecipesListModel();
        recipesListModel.recipes = new ArrayList<RecipeModel>();

        String select_query  = "SELECT * FROM " + RECIPE_TABLE;

        Cursor cursor = null;
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        cursor = mReadableDB.rawQuery(select_query, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    RecipeModel entry = new RecipeModel();
                    entry.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(RECIPE_ID))));
                    entry.setRecipeName(cursor.getString(cursor.getColumnIndex(NAME)));
                    entry.setRecipeTime(cursor.getString(cursor.getColumnIndex(TIME)));
                    entry.setRecipeIngredients(cursor.getString(cursor.getColumnIndex(INGREDIENTS)));
                    entry.setRecipeDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                    recipesListModel.recipes.add(entry);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return  recipesListModel;
    }

    // Select RecModel
    public RecModel selectRecipe(){

        String select_query  = "SELECT * FROM " + RECIPE_TABLE;

        Cursor cursor = null;
        RecModel entry = new RecModel();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(select_query, null);
            cursor.moveToFirst();
            entry.setID(cursor.getInt(cursor.getColumnIndex(RECIPE_ID)));
            entry.setNAME(cursor.getString(cursor.getColumnIndex(NAME)));
            entry.setTIME(cursor.getString(cursor.getColumnIndex(TIME)));
            entry.setINGREDIENTS(cursor.getString(cursor.getColumnIndex(INGREDIENTS)));
            entry.setDESCRIPTION(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));

        }
        catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    //Update RecModel
    public int updateRecipe(int id, String name, String time, String ingredients, String description) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {mWritableDB = getWritableDatabase();}
            ContentValues values = new ContentValues();
            values.put(NAME, name);
            values.put(TIME,time);
            values.put(INGREDIENTS,ingredients);
            values.put(DESCRIPTION,description);

            mNumberOfRowsUpdated = mWritableDB.update(RECIPE_TABLE, //table to change
                    values, // new values to insert
                    RECIPE_ID + " = ?", // selection criteria for row (in this case, the _id column)
                    new String[]{String.valueOf(id)}); //selection args; the actual value of the id

        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }

    //Delete RecModel
    public int deleteRecipe(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(RECIPE_TABLE, //table name
                    RECIPE_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d(TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        return deleted;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserDetailsDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
        onCreate(db);
    }
}
