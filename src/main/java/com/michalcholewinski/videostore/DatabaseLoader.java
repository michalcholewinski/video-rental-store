package com.michalcholewinski.videostore;

import com.michalcholewinski.videostore.pricing.Tariff;
import com.michalcholewinski.videostore.pricing.TariffRepository;
import com.michalcholewinski.videostore.pricing.TariffType;
import com.michalcholewinski.videostore.video.Category;
import com.michalcholewinski.videostore.video.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Only for test purpose
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    public static final int PRICE_FOR_NEW_RELEASE_RENTAL = 40;
    public static final int PRICE_FOR_NEW_RELEASE_PENALTY = 40;
    public static final int PRICE_FOR_REGULAR_FILMS_FIRST_3_DAYS_RENTAL = 30;
    public static final int PRICE_FOR_REGULAR_FILMS_RENTAL = 30;
    public static final int PRICE_FOR_REGULAR_FILMS_PENALTY = 30;
    public static final int PRICE_FOR_OLD_FILMS_FIRST_5_DAYS_RENTAL = 30;
    public static final int PRICE_FOR_OLD_FILMS_RENTAL = 30;
    public static final int PRICE_FOR_OLD_FILMS_PENALTY = 30;

    private final CategoryRepository categoryRepository;
    private final TariffRepository tariffRepository;

    @Autowired
    public DatabaseLoader(CategoryRepository categoryRepository, TariffRepository tariffRepository) {
        this.categoryRepository = categoryRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Category newReleases = createCategory("New releases");
        createTariff(newReleases, PRICE_FOR_NEW_RELEASE_RENTAL * 100, "SEK", TariffType.RENTAL);
        createTariff(newReleases, PRICE_FOR_NEW_RELEASE_PENALTY * 100, "SEK", TariffType.PENALTY);

        Category regularFilms = createCategory("Regular films");
        createTariff(regularFilms, PRICE_FOR_REGULAR_FILMS_FIRST_3_DAYS_RENTAL * 100, "SEK", TariffType.RENTAL_FIRST_3_DAYS);
        createTariff(regularFilms, PRICE_FOR_REGULAR_FILMS_RENTAL * 100, "SEK", TariffType.RENTAL);
        createTariff(regularFilms, PRICE_FOR_REGULAR_FILMS_PENALTY * 100, "SEK", TariffType.PENALTY);

        Category oldFilms = createCategory("Old films");
        createTariff(oldFilms, PRICE_FOR_OLD_FILMS_FIRST_5_DAYS_RENTAL * 100, "SEK", TariffType.RENTAL_FIRST_5_DAYS);
        createTariff(oldFilms, PRICE_FOR_OLD_FILMS_RENTAL * 100, "SEK", TariffType.RENTAL);
        createTariff(oldFilms, PRICE_FOR_OLD_FILMS_PENALTY * 100, "SEK", TariffType.PENALTY);
    }

    private Category createCategory(String name) {
        return categoryRepository.save(new Category().setName(name));
    }

    private Tariff createTariff(Category category, int amount, String currencyCode, TariffType type) {
        return tariffRepository.save(Tariff.builder()
                .category(category)
                .amount(amount)
                .currencyCode(currencyCode)
                .type(type)
                .build());
    }
}
