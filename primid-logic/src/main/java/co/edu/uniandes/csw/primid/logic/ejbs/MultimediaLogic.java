package co.edu.uniandes.csw.primid.logic.ejbs;

import co.edu.uniandes.csw.bookstore.api.IBookLogic;
import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import co.edu.uniandes.csw.primid.logic.entities.MultimediaEntity;
import co.edu.uniandes.csw.primid.logic.persistence.MultimediaPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MultimediaLogic {

    private static final Logger logger = Logger.getLogger(BookLogic.class.getName());

    @Inject
    private MultimediaPersistence persistence;



    public List<MultimediaEntity> getArchivosMultimedia() {
        logger.info("Inicia proceso de consultar todos los libros");
        List<MultimediaEntity> archivosMultimedia = persistence.findAll();
        logger.info("Termina proceso de consultar todos los libros");
        return archivosMultimedia;
    }



}
