package br.com.pendragon.model.geometry;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * A class representing a node in a 3D finite element model.
 */
public class Node3D {

    private String name;    // A unique name for the node assigned by the user
    private Integer ID;     // A unique index number for the node assigned by the program

    private double X;       // Global X coordinate
    private double Y;       // Global Y coordinate
    private double Z;       // Global Z coordinate

    private List<Load> nodeLoads; // A list of loads applied to the node (Direction, P, case) or (Direction, M, case)

    // Dictionaries of calculated node displacements
    private Map<String, Double> DX;
    private Map<String, Double> DY;
    private Map<String, Double> DZ;
    private Map<String, Double> RX;
    private Map<String, Double> RY;
    private Map<String, Double> RZ;

    // Dictionaries of calculated node reactions
    private Map<String, Double> RxnFX;
    private Map<String, Double> RxnFY;
    private Map<String, Double> RxnFZ;
    private Map<String, Double> RxnMX;
    private Map<String, Double> RxnMY;
    private Map<String, Double> RxnMZ;

    // Support conditions
    private boolean supportDX;
    private boolean supportDY;
    private boolean supportDZ;
    private boolean supportRX;
    private boolean supportRY;
    private boolean supportRZ;

    // Support springs
    private Spring springDX;
    private Spring springDY;
    private Spring springDZ;
    private Spring springRX;
    private Spring springRY;
    private Spring springRZ;

    // Enforced displacements
    private Double enforcedDX;
    private Double enforcedDY;
    private Double enforcedDZ;
    private Double enforcedRX;
    private Double enforcedRY;
    private Double enforcedRZ;

    // Color contour value
    private List<Double> contour;

    /**
     * Inner class to represent a load applied to the node.
     */
    public static class Load {
        // Define fields for direction, magnitude, and case here
        // Add methods for Load as needed
    }

    /**
     * Inner class to represent a spring with stiffness and direction.
     */
    public static class Spring {
        private Double stiffness;  // Stiffness of the spring
        private Integer direction; // Direction of the spring
        private Boolean active;    // Whether the spring is active or not

        // Constructors, getters, and setters for Spring
        public Spring() {
            this.stiffness = null;
            this.direction = null;
            this.active = null;
        }

        public Double getStiffness() {
            return stiffness;
        }

        public void setStiffness(Double stiffness) {
            this.stiffness = stiffness;
        }

        public Integer getDirection() {
            return direction;
        }

        public void setDirection(Integer direction) {
            this.direction = direction;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }
    }

    /**
     * Constructs a new Node3D instance.
     *
     * @param name The unique name for the node.
     * @param X The global X coordinate of the node.
     * @param Y The global Y coordinate of the node.
     * @param Z The global Z coordinate of the node.
     */
    public Node3D(String name, double X, double Y, double Z) {
        this.name = name;
        this.X = X;
        this.Y = Y;
        this.Z = Z;

        this.nodeLoads = new ArrayList<>();
        this.DX = new HashMap<>();
        this.DY = new HashMap<>();
        this.DZ = new HashMap<>();
        this.RX = new HashMap<>();
        this.RY = new HashMap<>();
        this.RZ = new HashMap<>();

        this.RxnFX = new HashMap<>();
        this.RxnFY = new HashMap<>();
        this.RxnFZ = new HashMap<>();
        this.RxnMX = new HashMap<>();
        this.RxnMY = new HashMap<>();
        this.RxnMZ = new HashMap<>();

        this.supportDX = false;
        this.supportDY = false;
        this.supportDZ = false;
        this.supportRX = false;
        this.supportRY = false;
        this.supportRZ = false;

        this.springDX = new Spring();
        this.springDY = new Spring();
        this.springDZ = new Spring();
        this.springRX = new Spring();
        this.springRY = new Spring();
        this.springRZ = new Spring();

        this.enforcedDX = null;
        this.enforcedDY = null;
        this.enforcedDZ = null;
        this.enforcedRX = null;
        this.enforcedRY = null;
        this.enforcedRZ = null;

        this.contour = new ArrayList<>();
    }

    /**
     * Gets the name of the node.
     *
     * @return The name of the node.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the node.
     *
     * @param name The name of the node.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the node.
     *
     * @return The ID of the node.
     */
    public Integer getID() {
        return ID;
    }

    /**
     * Sets the ID of the node.
     *
     * @param ID The ID of the node.
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * Gets the X coordinate of the node.
     *
     * @return The X coordinate.
     */
    public double getX() {
        return X;
    }

    /**
     * Sets the X coordinate of the node.
     *
     * @param X The X coordinate.
     */
    public void setX(double X) {
        this.X = X;
    }

    /**
     * Gets the Y coordinate of the node.
     *
     * @return The Y coordinate.
     */
    public double getY() {
        return Y;
    }

    /**
     * Sets the Y coordinate of the node.
     *
     * @param Y The Y coordinate.
     */
    public void setY(double Y) {
        this.Y = Y;
    }

    /**
     * Gets the Z coordinate of the node.
     *
     * @return The Z coordinate.
     */
    public double getZ() {
        return Z;
    }

    /**
     * Sets the Z coordinate of the node.
     *
     * @param Z The Z coordinate.
     */
    public void setZ(double Z) {
        this.Z = Z;
    }

    /**
     * Gets the list of loads applied to the node.
     *
     * @return The list of loads.
     */
    public List<Load> getNodeLoads() {
        return nodeLoads;
    }

    /**
     * Sets the list of loads applied to the node.
     *
     * @param nodeLoads The list of loads.
     */
    public void setNodeLoads(List<Load> nodeLoads) {
        this.nodeLoads = nodeLoads;
    }

    /**
     * Gets the dictionary of calculated node displacements in X direction.
     *
     * @return The dictionary of X displacements.
     */
    public Map<String, Double> getDX() {
        return DX;
    }

    /**
     * Sets the dictionary of calculated node displacements in X direction.
     *
     * @param DX The dictionary of X displacements.
     */
    public void setDX(Map<String, Double> DX) {
        this.DX = DX;
    }

    /**
     * Gets the dictionary of calculated node displacements in Y direction.
     *
     * @return The dictionary of Y displacements.
     */
    public Map<String, Double> getDY() {
        return DY;
    }

    /**
     * Sets the dictionary of calculated node displacements in Y direction.
     *
     * @param DY The dictionary of Y displacements.
     */
    public void setDY(Map<String, Double> DY) {
        this.DY = DY;
    }

    /**
     * Gets the dictionary of calculated node displacements in Z direction.
     *
     * @return The dictionary of Z displacements.
     */
    public Map<String, Double> getDZ() {
        return DZ;
    }

    /**
     * Sets the dictionary of calculated node displacements in Z direction.
     *
     * @param DZ The dictionary of Z displacements.
     */
    public void setDZ(Map<String, Double> DZ) {
        this.DZ = DZ;
    }

    /**
     * Gets the dictionary of calculated node reactions in X direction.
     *
     * @return The dictionary of X reactions.
     */
    public Map<String, Double> getRxnFX() {
        return RxnFX;
    }

    /**
     * Sets the dictionary of calculated node reactions in X direction.
     *
     * @param RxnFX The dictionary of X reactions.
     */
    public void setRxnFX(Map<String, Double> RxnFX) {
        this.RxnFX = RxnFX;
    }

    /**
     * Gets the dictionary of calculated node reactions in Y direction.
     *
     * @return The dictionary of Y reactions.
     */
    public Map<String, Double> getRxnFY() {
        return RxnFY;
    }

    /**
     * Sets the dictionary of calculated node reactions in Y direction.
     *
     * @param RxnFY The dictionary of Y reactions.
     */
    public void setRxnFY(Map<String, Double> RxnFY) {
        this.RxnFY = RxnFY;
    }

    /**
     * Gets the dictionary of calculated node reactions in Z direction.
     *
     * @return The dictionary of Z reactions.
     */
    public Map<String, Double> getRxnFZ() {
        return RxnFZ;
    }

    /**
     * Sets the dictionary of calculated node reactions in Z direction.
     *
     * @param RxnFZ The dictionary of Z reactions.
     */
    public void setRxnFZ(Map<String, Double> RxnFZ) {
        this.RxnFZ = RxnFZ;
    }

    /**
     * Gets the dictionary of calculated node reactions in X moment.
     *
     * @return The dictionary of X moments.
     */
    public Map<String, Double> getRxnMX() {
        return RxnMX;
    }

    /**
     * Sets the dictionary of calculated node reactions in X moment.
     *
     * @param RxnMX The dictionary of X moments.
     */
    public void setRxnMX(Map<String, Double> RxnMX) {
        this.RxnMX = RxnMX;
    }

    /**
     * Gets the dictionary of calculated node reactions in Y moment.
     *
     * @return The dictionary of Y moments.
     */
    public Map<String, Double> getRxnMY() {
        return RxnMY;
    }

    /**
     * Sets the dictionary of calculated node reactions in Y moment.
     *
     * @param RxnMY The dictionary of Y moments.
     */
    public void setRxnMY(Map<String, Double> RxnMY) {
        this.RxnMY = RxnMY;
    }

    /**
     * Gets the dictionary of calculated node reactions in Z moment.
     *
     * @return The dictionary of Z moments.
     */
    public Map<String, Double> getRxnMZ() {
        return RxnMZ;
    }

    /**
     * Sets the dictionary of calculated node reactions in Z moment.
     *
     * @param RxnMZ The dictionary of Z moments.
     */
    public void setRxnMZ(Map<String, Double> RxnMZ) {
        this.RxnMZ = RxnMZ;
    }

    /**
     * Checks if the node is supported in X direction.
     *
     * @return True if supported in X direction, otherwise false.
     */
    public boolean isSupportDX() {
        return supportDX;
    }

    /**
     * Sets the support condition for X direction.
     *
     * @param supportDX True if supported in X direction, otherwise false.
     */
    public void setSupportDX(boolean supportDX) {
        this.supportDX = supportDX;
    }

    /**
     * Checks if the node is supported in Y direction.
     *
     * @return True if supported in Y direction, otherwise false.
     */
    public boolean isSupportDY() {
        return supportDY;
    }

    /**
     * Sets the support condition for Y direction.
     *
     * @param supportDY True if supported in Y direction, otherwise false.
     */
    public void setSupportDY(boolean supportDY) {
        this.supportDY = supportDY;
    }

    /**
     * Checks if the node is supported in Z direction.
     *
     * @return True if supported in Z direction, otherwise false.
     */
    public boolean isSupportDZ() {
        return supportDZ;
    }

    /**
     * Sets the support condition for Z direction.
     *
     * @param supportDZ True if supported in Z direction, otherwise false.
     */
    public void setSupportDZ(boolean supportDZ) {
        this.supportDZ = supportDZ;
    }

    /**
     * Checks if the node is supported in X rotation.
     *
     * @return True if supported in X rotation, otherwise false.
     */
    public boolean isSupportRX() {
        return supportRX;
    }

    /**
     * Sets the support condition for X rotation.
     *
     * @param supportRX True if supported in X rotation, otherwise false.
     */
    public void setSupportRX(boolean supportRX) {
        this.supportRX = supportRX;
    }

    /**
     * Checks if the node is supported in Y rotation.
     *
     * @return True if supported in Y rotation, otherwise false.
     */
    public boolean isSupportRY() {
        return supportRY;
    }

    /**
     * Sets the support condition for Y rotation.
     *
     * @param supportRY True if supported in Y rotation, otherwise false.
     */
    public void setSupportRY(boolean supportRY) {
        this.supportRY = supportRY;
    }

    /**
     * Checks if the node is supported in Z rotation.
     *
     * @return True if supported in Z rotation, otherwise false.
     */
    public boolean isSupportRZ() {
        return supportRZ;
    }

    /**
     * Sets the support condition for Z rotation.
     *
     * @param supportRZ True if supported in Z rotation, otherwise false.
     */
    public void setSupportRZ(boolean supportRZ) {
        this.supportRZ = supportRZ;
    }

    /**
     * Gets the spring supporting X direction.
     *
     * @return The spring in X direction.
     */
    public Spring getSpringDX() {
        return springDX;
    }

    /**
     * Sets the spring supporting X direction.
     *
     * @param springDX The spring in X direction.
     */
    public void setSpringDX(Spring springDX) {
        this.springDX = springDX;
    }

    /**
     * Gets the spring supporting Y direction.
     *
     * @return The spring in Y direction.
     */
    public Spring getSpringDY() {
        return springDY;
    }

    /**
     * Sets the spring supporting Y direction.
     *
     * @param springDY The spring in Y direction.
     */
    public void setSpringDY(Spring springDY) {
        this.springDY = springDY;
    }

    /**
     * Gets the spring supporting Z direction.
     *
     * @return The spring in Z direction.
     */
    public Spring getSpringDZ() {
        return springDZ;
    }

    /**
     * Sets the spring supporting Z direction.
     *
     * @param springDZ The spring in Z direction.
     */
    public void setSpringDZ(Spring springDZ) {
        this.springDZ = springDZ;
    }

    /**
     * Gets the spring supporting X rotation.
     *
     * @return The spring in X rotation.
     */
    public Spring getSpringRX() {
        return springRX;
    }

    /**
     * Sets the spring supporting X rotation.
     *
     * @param springRX The spring in X rotation.
     */
    public void setSpringRX(Spring springRX) {
        this.springRX = springRX;
    }

    /**
     * Gets the spring supporting Y rotation.
     *
     * @return The spring in Y rotation.
     */
    public Spring getSpringRY() {
        return springRY;
    }

    /**
     * Sets the spring supporting Y rotation.
     *
     * @param springRY The spring in Y rotation.
     */
    public void setSpringRY(Spring springRY) {
        this.springRY = springRY;
    }

    /**
     * Gets the spring supporting Z rotation.
     *
     * @return The spring in Z rotation.
     */
    public Spring getSpringRZ() {
        return springRZ;
    }

    /**
     * Sets the spring supporting Z rotation.
     *
     * @param springRZ The spring in Z rotation.
     */
    public void setSpringRZ(Spring springRZ) {
        this.springRZ = springRZ;
    }

    /**
     * Gets the enforced displacement in X direction.
     *
     * @return The enforced displacement in X direction.
     */
    public Double getEnforcedDX() {
        return enforcedDX;
    }

    /**
     * Sets the enforced displacement in X direction.
     *
     * @param enforcedDX The enforced displacement in X direction.
     */
    public void setEnforcedDX(Double enforcedDX) {
        this.enforcedDX = enforcedDX;
    }

    /**
     * Gets the enforced displacement in Y direction.
     *
     * @return The enforced displacement in Y direction.
     */
    public Double getEnforcedDY() {
        return enforcedDY;
    }

    /**
     * Sets the enforced displacement in Y direction.
     *
     * @param enforcedDY The enforced displacement in Y direction.
     */
    public void setEnforcedDY(Double enforcedDY) {
        this.enforcedDY = enforcedDY;
    }

    /**
     * Gets the enforced displacement in Z direction.
     *
     * @return The enforced displacement in Z direction.
     */
    public Double getEnforcedDZ() {
        return enforcedDZ;
    }

    /**
     * Sets the enforced displacement in Z direction.
     *
     * @param enforcedDZ The enforced displacement in Z direction.
     */
    public void setEnforcedDZ(Double enforcedDZ) {
        this.enforcedDZ = enforcedDZ;
    }

    /**
     * Gets the enforced rotation in X direction.
     *
     * @return The enforced rotation in X direction.
     */
    public Double getEnforcedRX() {
        return enforcedRX;
    }

    /**
     * Sets the enforced rotation in X direction.
     *
     * @param enforcedRX The enforced rotation in X direction.
     */
    public void setEnforcedRX(Double enforcedRX) {
        this.enforcedRX = enforcedRX;
    }

    /**
     * Gets the enforced rotation in Y direction.
     *
     * @return The enforced rotation in Y direction.
     */
    public Double getEnforcedRY() {
        return enforcedRY;
    }

    /**
     * Sets the enforced rotation in Y direction.
     *
     * @param enforcedRY The enforced rotation in Y direction.
     */
    public void setEnforcedRY(Double enforcedRY) {
        this.enforcedRY = enforcedRY;
    }

    /**
     * Gets the enforced rotation in Z direction.
     *
     * @return The enforced rotation in Z direction.
     */
    public Double getEnforcedRZ() {
        return enforcedRZ;
    }

    /**
     * Sets the enforced rotation in Z direction.
     *
     * @param enforcedRZ The enforced rotation in Z direction.
     */
    public void setEnforcedRZ(Double enforcedRZ) {
        this.enforcedRZ = enforcedRZ;
    }

    /**
     * Gets the color contour value for the node.
     *
     * @return The list of contour values.
     */
    public List<Double> getContour() {
        return contour;
    }

    /**
     * Sets the color contour value for the node.
     *
     * @param contour The list of contour values.
     */
    public void setContour(List<Double> contour) {
        this.contour = contour;
    }

    /**
     * Calculates the distance to another node.
     *
     * @param other The other node to compare with.
     * @return The distance to the other node.
     */
    public double distance(Node3D other) {
        return Math.sqrt(Math.pow(this.X - other.X, 2) +
                Math.pow(this.Y - other.Y, 2) +
                Math.pow(this.Z - other.Z, 2));
    }
}
