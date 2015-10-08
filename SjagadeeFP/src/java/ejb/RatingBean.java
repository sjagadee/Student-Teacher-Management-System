/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import domain.Rating;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class RatingBean extends AbstractBean<Rating> {

    public RatingBean() {
        super(Rating.class);
    }

    @Override
    public List<Rating> findAll() {
        return getEntityManager().createNamedQuery("Rating.findAll",Rating.class).getResultList();
    }
    
}
