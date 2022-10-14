package reznikov.promo.services;

import reznikov.promo.models.Company;

public class ITReaccreditationProcess implements ReaccreditationProcess {
    @Override
    public void process(Company company) {
        System.out.println("Reaccreditation of IT Company with name " + company.getName());
    }
}