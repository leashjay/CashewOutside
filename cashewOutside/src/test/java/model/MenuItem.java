package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */

public class MenuItem {
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public boolean isVegetarian;
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public boolean isVegan;
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public ArrayList<Ingredient> recipe;
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public int salesPrice;
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public int costToMake;
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public Set<Menu> menu;
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private boolean isGlutenFree;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public MenuItem() {
        super();
    }

    public MenuItem(String id, String name, List<String> ingredientNames) {
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public MenuItem createRecipe(ArrayList<Ingredient> parameter) {
        // TODO implement me
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public Ingredient addToRecipe(Ingredient parameter) {
        // TODO implement me
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    public Ingredient removeFromRecipe(Ingredient parameter) {
        // TODO implement me
        return null;
    }

}

