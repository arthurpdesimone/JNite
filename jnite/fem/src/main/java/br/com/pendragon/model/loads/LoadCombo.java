package br.com.pendragon.model.loads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class that stores all the information necessary to define a load combination.
 */
public class LoadCombo {

    private String name; // A unique user-defined name for the load combination
    private List<String> comboTags; // Used to categorize the load combination (e.g., strength or serviceability)
    private Map<String, Double> factors; // A dictionary containing each load case name and associated load factor

    /**
     * Initializes a new load combination.
     *
     * @param name The unique name for the load combination.
     * @param comboTags A list of tags for the load combination. This is a list of any strings you would like to use to categorize your load combinations.
     * @param factors A dictionary of load case names (keys) followed by their load factors (items).
     */
    public LoadCombo(String name, List<String> comboTags, Map<String, Double> factors) {
        this.name = name;
        this.comboTags = comboTags;
        this.factors = factors != null ? factors : new HashMap<>();
    }

    /**
     * Gets the name of the load combination.
     *
     * @return The name of the load combination.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the load combination.
     *
     * @param name The unique name for the load combination.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the list of tags for the load combination.
     *
     * @return The list of tags.
     */
    public List<String> getComboTags() {
        return comboTags;
    }

    /**
     * Sets the list of tags for the load combination.
     *
     * @param comboTags The list of tags to categorize the load combination.
     */
    public void setComboTags(List<String> comboTags) {
        this.comboTags = comboTags;
    }

    /**
     * Gets the dictionary of load case names and associated load factors.
     *
     * @return The dictionary of load cases and factors.
     */
    public Map<String, Double> getFactors() {
        return factors;
    }

    /**
     * Sets the dictionary of load case names and associated load factors.
     *
     * @param factors The dictionary of load cases and factors.
     */
    public void setFactors(Map<String, Double> factors) {
        this.factors = factors;
    }

    /**
     * Adds a load case with its associated load factor.
     *
     * @param caseName The name of the load case.
     * @param factor The load factor associated with the load case.
     */
    public void addLoadCase(String caseName, double factor) {
        factors.put(caseName, factor);
    }

    /**
     * Deletes a load case with its associated load factor.
     *
     * @param caseName The name of the load case to be deleted.
     */
    public void deleteLoadCase(String caseName) {
        factors.remove(caseName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoadCombo loadCombo = (LoadCombo) o;
        return Objects.equals(name, loadCombo.name) &&
                Objects.equals(comboTags, loadCombo.comboTags) &&
                Objects.equals(factors, loadCombo.factors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, comboTags, factors);
    }

    @Override
    public String toString() {
        return "LoadCombo{" +
                "name='" + name + '\'' +
                ", comboTags=" + comboTags +
                ", factors=" + factors +
                '}';
    }
}
