/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.primid.logic.ejbs;

import co.edu.uniandes.csw.primid.logic.api.ICiudadLogic;
import co.edu.uniandes.csw.primid.logic.entities.CiudadEntity;
import co.edu.uniandes.csw.primid.logic.persistence.CiudadPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.torres11
 */
@RunWith(Arquillian.class)
public class CiudadLogicTest {

    public CiudadLogicTest() {
    }
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ICiudadLogic ciudadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CiudadEntity> data = new ArrayList<CiudadEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadLogic.class.getPackage())
                .addPackage(ICiudadLogic.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }

    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            em.persist(entity);
            data.add(entity);

            //booksData.get(0).getAuthors().add(entity);
        }
    }

    @Test
    public void createCiudadTest() {
        CiudadEntity expected = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity created = ciudadLogic.createCiudad(expected);

        CiudadEntity result = em.find(CiudadEntity.class, created.getId());

        Assert.assertNotNull(result);
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.getId(), result.getId());
        Assert.assertEquals(expected.getName(), result.getName());
        Assert.assertEquals(expected.getCoordenadas(), result.getCoordenadas());
    }
    
    @Test
    public void getCiudadesTest() {
        List<CiudadEntity> resultList = ciudadLogic.getCiudades();
        List<CiudadEntity> expectedList = em.createQuery("SELECT u from CiudadEntity u").getResultList();
        Assert.assertEquals(expectedList.size(), resultList.size());
        for (CiudadEntity expected : expectedList) {
            boolean found = false;
            for (CiudadEntity result : resultList) {
                if (result.getId().equals(expected.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void deleteCiudadTest() {
        CiudadEntity entity = data.get(1);
        ciudadLogic.deleteCiudad(entity.getId());
        CiudadEntity expected = em.find(CiudadEntity.class, entity.getId());
        Assert.assertNull(expected);
    }
    
    @Test
    public void updateCiudadTest() {
        CiudadEntity entity = data.get(0);
        CiudadEntity expected = factory.manufacturePojo(CiudadEntity.class);

        expected.setId(entity.getId());

        ciudadLogic.updateCiudad(expected);

        CiudadEntity resp = em.find(CiudadEntity.class, entity.getId());

        Assert.assertNotNull(expected);
        Assert.assertEquals(expected.getId(), resp.getId());
        Assert.assertEquals(expected.getName(), resp.getName());
        Assert.assertEquals(expected.getCoordenadas(), resp.getCoordenadas());
     
    }

}
