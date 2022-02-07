package br.com.itau.neptunepoc.api.partner.controller;

import br.com.itau.neptunepoc.api.partner.request.PartnerRequest;
import br.com.itau.neptunepoc.api.partner.response.PartnerResponse;
import br.com.itau.neptunepoc.constants.RotasAPIV1;
import br.com.itau.neptunepoc.service.RDPService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = RotasAPIV1.PATH)
public class PartnerController {

    private RDPService rdpService;

    @PostMapping(value = RotasAPIV1.CREATE_RELATIONSHIP_PARTNER)
    public PartnerResponse create(@RequestBody PartnerRequest partnerRequest) {
        return rdpService.createRelationship(partnerRequest);
    }

//    @GetMapping(value = RotasAPIV1.GET_RELATIONSHIP_PARTNER)
//    public List<PartnerResponse> getRelationship(@PathVariable(name = LabelConstants.ID_CLIENTE_PAI) String idClientePai){
//        return rdpService.getRelationship(idClientePai);
//    }

//    @PatchMapping(value = RotasAPIV1.PATCH_RELATIONSHIP_PARTNER)
//    public Edge pacthRelationship(@RequestBody InformacoesAdicionais informacoesAdicionais,
//                                  @PathVariable(name = LabelConstants.ID_RELACIONAMENTO) String idRelacionamento){
//        return rdpService.patchRelationship(idRelacionamento, informacoesAdicionais);
//    }

//    @DeleteMapping(value = RotasAPIV1.DELETE_RELATIONSHIP_PARTNER)
//    public void pacthRelationship(@PathVariable(name = LabelConstants.ID_RELACIONAMENTO) String idRelacionamento){
//        rdpService.deleteRelationship(idRelacionamento);
//    }

}
