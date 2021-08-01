package reservations;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private AccountRepository acc;

    @Autowired
    private ReservationRepository resRepo;

    @GetMapping("/reservations")
    public String home(Model mo) {

        mo.addAttribute("reservations", resRepo.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(Authentication auth,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {

        List<Reservation> rese = resRepo.findAll();
        List<LocalDate> from = rese.stream().map(s -> s.getReservationFrom()).sorted().collect(Collectors.toList());
        List<LocalDate> to = rese.stream().map(s -> s.getReservationTo()).sorted().collect(Collectors.toList());
        LocalDate first = from.get(0);
        LocalDate last = to.get(to.size() - 1);

        if (reservationFrom.compareTo(last)>0 && reservationTo.compareTo(last)>0) {
            resRepo.save(new Reservation(acc.findByUsername(auth.getName()), reservationFrom, reservationTo));
        }

        return "redirect:/reservations";
    }

}
