package com.esprit.examen.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class fournisseur {
    @Mock
    FournisseurRepository Repo;

    @InjectMocks
    FournisseurServiceImpl Service;

    Fournisseur fournisseur = Fournisseur.builder().code("11").libelle(null).build();
    List<Fournisseur> listFournisseur = new ArrayList<Fournisseur>() {
        {
            add(Fournisseur.builder().code("10").libelle(null).build());
            add(Fournisseur.builder().code("12").libelle(null).build());
        }
    };

    @Test
    void testRetrieveFournisseur() {
        Mockito.when(Repo.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        @SuppressWarnings("removal")
        Fournisseur s1 = Service.retrieveFournisseur(new Long(2));
        Assertions.assertNotNull(s1);
    }

    @Test
    void testAllRetrieveFournisseur() {
        Mockito.when(Repo.findAll()).thenReturn(listFournisseur);
        List<Fournisseur> IFournisseur = Service.retrieveAllFournisseurs();
        Assertions.assertNotNull(IFournisseur);
    }

    @Test
    void testAddfournisseur() {
        Mockito.when(Repo.save(fournisseur)).thenReturn(fournisseur);
        Fournisseur s1 = Service.addFournisseur(fournisseur);
        Assertions.assertNotNull(s1);

    }

    @Test
    void testUpdatefournisseur() {
    	
    	Mockito.when(Repo.findById(fournisseur.getIdFournisseur())).thenReturn(Optional.of(fournisseur));
    	Fournisseur f1 = Service.retrieveFournisseur(fournisseur.getIdFournisseur());
    	f1.setCode("loulou");
        Mockito.when(Repo.save(f1)).thenReturn(f1);
        Assertions.assertEquals(f1.getCode(), Service.updateFournisseur(f1).getCode());

    }
    
    

    @Test
    void testDeletefournisseur() {
        Service.deleteFournisseur(fournisseur.getIdFournisseur());
        Mockito.verify(Repo, Mockito.times(1)).deleteById(fournisseur.getIdFournisseur());
        System.out.println("aaaa");
    }


}