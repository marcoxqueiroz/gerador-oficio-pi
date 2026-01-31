package br.senac.pi.oficio.ui;

import br.senac.pi.oficio.application.GenerateOficioUseCase;
import br.senac.pi.oficio.application.GetLastOficioUseCase;
import br.senac.pi.oficio.domain.OficioGenerator;
import br.senac.pi.oficio.domain.OficioNumber;
import br.senac.pi.oficio.infra.InMemoryOficioRepository;

import java.time.Year;
import java.util.Optional;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        // Infra
        InMemoryOficioRepository repo = new InMemoryOficioRepository();

        // Domain
        OficioGenerator generator = new OficioGenerator();

        // Application (use cases)
        GenerateOficioUseCase generateUseCase = new GenerateOficioUseCase(repo, generator);
        GetLastOficioUseCase getLastUseCase = new GetLastOficioUseCase(repo);

        // Testes rápidos no main() (evidência)
        System.out.println("== Teste automático ==");
        int y = Year.now().getValue();
        System.out.println("Gerando 3 números no ano " + y + ":");
        System.out.println(generateUseCase.execute(y));
        System.out.println(generateUseCase.execute(y));
        System.out.println(generateUseCase.execute(y));
        System.out.println("Último do ano " + y + ": " + getLastUseCase.execute(y).orElse(null));
        System.out.println("======================\n");

        // Menu simples
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Gerador de Número de Ofício ===");
            System.out.println("1) Gerar próximo número (ano atual)");
            System.out.println("2) Gerar próximo número (informar ano)");
            System.out.println("3) Consultar último número de um ano");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");

            String opt = sc.nextLine().trim();

            try {
                switch (opt) {
                    case "1": {
                        int year = Year.now().getValue();
                        OficioNumber n = generateUseCase.execute(year);
                        System.out.println("Gerado: " + n.format());
                        break;
                    }
                    case "2": {
                        System.out.print("Ano (ex: 2026): ");
                        int year = Integer.parseInt(sc.nextLine().trim());
                        OficioNumber n = generateUseCase.execute(year);
                        System.out.println("Gerado: " + n.format());
                        break;
                    }
                    case "3": {
                        System.out.print("Ano (ex: 2026): ");
                        int year = Integer.parseInt(sc.nextLine().trim());
                        Optional<OficioNumber> last = getLastUseCase.execute(year);
                        if (last.isPresent()) {
                            System.out.println("Último: " + last.get().format());
                        } else {
                            System.out.println("Ainda não existe ofício gerado para esse ano.");
                        }
                        break;
                    }
                    case "0":
                        System.out.println("Encerrando.");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }

            System.out.println();
        }
    }
}
