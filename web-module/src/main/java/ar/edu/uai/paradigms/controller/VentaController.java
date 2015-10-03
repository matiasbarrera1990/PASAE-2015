package ar.edu.uai.paradigms.controller;

import ar.edu.uai.model.Venta;
import ar.edu.uai.paradigms.authentication.SimpleAuthenticationProvider;
import ar.edu.uai.paradigms.dto.CompraDTO;
import ar.edu.uai.paradigms.dto.VentaDTO;
import ar.edu.uai.paradigms.service.VentaService;
import ar.edu.uai.paradigms.translator.CompraTranslator;
import ar.edu.uai.paradigms.translator.VentaTranslator;
import ar.edu.uai.paradigms.validators.VentaDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/venta")
public class VentaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VentaController.class);

	private VentaService ventaService;

	private VentaTranslator ventaTranslator;

	private CompraTranslator compraTranslator;
	

	public VentaController(VentaService ventaService, VentaTranslator ventaTranslator, CompraTranslator compraTranslator) {
		super();
		this.ventaService = ventaService;
		this.ventaTranslator = ventaTranslator;
		this.compraTranslator=compraTranslator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new VentaDTOValidator());
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody VentaDTO createVenta(@RequestBody  VentaDTO ventaDTO) {
		LOGGER.debug("Received DTO: " + ventaDTO);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return this.ventaTranslator.translateToDTO(this.ventaService.saveVenta(this.ventaTranslator.translate(ventaDTO),ventaDTO.getFuncionId(),ventaDTO.getNumeroTarjeta(),ventaDTO.getCvv(),SimpleAuthenticationProvider.getUserLogged()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{identifier}")
	public @ResponseBody VentaDTO getVenta(@PathVariable long identifier) {
		return this.ventaTranslator.translateToDTO(this.ventaService.retrieveVenta(identifier));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/miscompras/{username:.+}")
	public
	@ResponseBody
	Collection<CompraDTO> misCompras(@PathVariable String username) {
		Collection<CompraDTO> misCompras = new ArrayList<CompraDTO>();

		Collection<Venta> compras = (this.ventaService.listarComprasDeEspectador(username));
		for (Venta v : compras) {
			misCompras.add(this.compraTranslator.translateToDTO(v));
		}
		return misCompras;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idsector}/{cantidadasientos}")
	public @ResponseBody VentaDTO calcularCostoVenta(@PathVariable Long idsector, @PathVariable Integer cantidadasientos) {
		Venta venta = new Venta();
		venta.setMonto(ventaService.calcularMontoFinal(idsector, cantidadasientos));
		return this.ventaTranslator.translateMontoToDTO(venta);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listado")
	public @ResponseBody
	Collection<VentaDTO> ventasRealizadas() {
		Collection<VentaDTO> misCompras = new ArrayList<VentaDTO>();

		Collection<Venta> compras = (this.ventaService.listarVentas());
		for (Venta v : compras) {
			misCompras.add(this.ventaTranslator.translateToDTO(v));
		}
		return misCompras;
	}





}