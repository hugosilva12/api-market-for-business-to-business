package ModuleCost;

import ModuleTransaction.Orgcom.Entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class that stores the distance between all districts
 */
public class DistanceTable implements IDistanceTable {

    private ArrayList<IDistrictDistance> basicDistrictsDistances = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    public IDistrictDistance getDistrictDistance(IDistrictDistance districtCost) {
        if (districtCost == null) {
            throw new IllegalArgumentException("DistrictCost cannot be nul.");
        }

        for (IDistrictDistance districtCostTemp : this.basicDistrictsDistances) {
            if (districtCost.getHash().equals(districtCostTemp.getHash())) {
                return districtCost;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addDistrictDistance(IDistrictDistance districtCost) {
        if(districtCost == null) {
            throw new IllegalArgumentException("districtCost cannot be null");
        }

        if (this.getDistrictDistance(districtCost) != null) {
            return false;
        }

        this.basicDistrictsDistances.add(districtCost);

        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getDistanceValueFromShipping(Entity sender, Entity receiver) {
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("The entities can't be null.");
        }

        if (sender.getDistrict().equals(receiver.getDistrict())) {
            return 0;
        }

        for (int i = 0; i < this.basicDistrictsDistances.size(); i++) {
            if (sender.getDistrict().equals(this.basicDistrictsDistances.get(i).getName())) {

                for (int j = 0; j < this.basicDistrictsDistances.get(i).getDistances().size(); j++) {

                    if (receiver.getDistrict().equals(this.basicDistrictsDistances.get(i).getDistances().get(j).getId())) {
                        return this.basicDistrictsDistances.get(i).getDistances().get(j).getDistanceValue();
                    }
                }
            }
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("DistrictCostTable{");
        for (int print = 0; print < this.basicDistrictsDistances.size(); print++) {
            sb.append("  DistrictCost{").append(this.basicDistrictsDistances.get(print).print());
        }
        sb.append("}");
        return sb.toString();
    }


    public Iterator<IDistrictDistance> iterator() {
        return this.basicDistrictsDistances.iterator();
    }
}
