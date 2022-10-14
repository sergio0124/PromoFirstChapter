package reznikov.promo.services;

import reznikov.promo.models.Company;

public class BuilderReaccreditationProcess implements ReaccreditationProcess {
    @Override
    public void process(Company company) {
        System.out.println("Reaccreditation of Builder Company with name " + company.getName());
    }
}
