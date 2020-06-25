package model;

import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;
import model.hotel.HotelBean;
import model.hotel.HotelDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;
import model.restaurant.RestaurantBean;
import model.restaurant.RestaurantDAO;
import model.tour.LuogoBean;
import model.tour.LuogoDAO;
import model.tour.TourBean;
import model.tour.TourDAO;

import java.sql.SQLException;
import java.util.UUID;

public class Popolamento {
    public static void main(String[] args) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        HotelDAO hotelDAO = new HotelDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        LuogoDAO luogoDAO = new LuogoDAO();
        TourDAO tourDAO = new TourDAO();
        PacchettoDAO pacchettoDAO = new PacchettoDAO();

        try {
            clienteDAO.doSave(new ClienteBean("Andrea", "Ercolino", 50, "a.ercolino6@studenti.unisa.it", "StayErk99.", false, "https://bit.ly/2BGONAN"));
            clienteDAO.doSave(new ClienteBean("Maria Chiara", "Nasto", 30, "m.nasto6@studenti.unisa.it", "Bataclan00", false, "https://bit.ly/2BGONAN"));
            clienteDAO.doSave(new ClienteBean("Giuseppe", "Cardaropoli", 10, "g.cardaropoli15@studenti.unisa.it", "Madonna118", false, "https://bit.ly/2BGONAN"));
            clienteDAO.doSave(new ClienteBean("YouPon", "YouPon", 0, "youpon@gmail.com", "admin", true, "https://bit.ly/31cCFm1"));

        } catch (SQLException throwables) {
            System.out.println("Errore popolamento Utenti");
            throwables.printStackTrace();
        }

        try {
            hotelDAO.doSave(new HotelBean(UUID.fromString("39867a1b-eb84-4fe6-9c80-4d973c6fa3b7"), "Hotel Roma", "Via Garibaldi 11", "Roma", 55, 5, "https://bit.ly/3hYzdkN", "pbrealey0@skype.com", "017145321"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("6a65bdd0-b5b5-4016-a9ee-85a6bbcf1ade"), "Hotel Rosa", "Corso Diaz 3", "Roma", 30, 3, "https://bit.ly/3dzn1Um", "bcoulthard0@ustream.tv", "013640923"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("cd21f109-8ea6-4f70-94e4-3b4668ab7cb7"), "Hotel Stella", "Largo Leone 9", "Firenze", 25, 3, "https://bit.ly/2BGXdrT", "lmcgilroy1@google.co.uk", "055352190"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("1990e400-fb93-4867-be79-0f36cf0f7ac3"), "Hotel Da Vinci", "Via Roma 15", "Firenze", 20, 3, "https://bit.ly/3fYo9SS", "jkenryd2@creativecommons.org", "05548651"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("dd19d5e0-920a-4a03-bc30-f15db9f1756c"), "Hotel Vista", "Via Scotti 69", "Angri", 40, 4, "https://bit.ly/3i0IQzA", "hbeadham3@hexun.com", "081890267"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("ba52aaaa-a5ab-4a04-92ed-bcc687ad881c"), "Hotel Nazionale", "Vico Equense 22", "Angri", 15, 2, "https://bit.ly/3eJSHHK", "baubrey4@cnbc.com", "081451293"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("798c621c-e695-4fe2-90a5-08f09ceebd33"), "Hotel Pineta", "Piazza Sordi 20", "Bari", 35, 3, "https://bit.ly/2VdYbD2", "dmorriarty5@dion.ne.jp", "080825770"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("2e46122e-10aa-4ef9-8afe-88997d5d30e9"), "Hotel Hiberia", "Via Nazionale 4", "Bari", 50, 4, "https://bit.ly/2BFQJcQ", "mwessell6@geocities.jp", "080908090"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("3b010399-bd87-4560-bc6f-505edf38b2a2"), "Hotel Paradiso", "Via Condotti 1", "Milano", 45, 4, "https://bit.ly/3dxjeXu", "tiacobetto7@tmall.com", "021678916"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("0a0ceb99-1970-4f97-90be-4cc80ade50c6"), "Hotel Pietro", "Via Veneto 88", "Milano", 10, 2, "https://bit.ly/3ezAwEC", "fgrishakin8@hatena.ne.jp", "020987268"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("5f37f3bf-84ba-45ab-a5dd-bb0ded21230c"), "Hotel Impero", "Via Fratellì 56", "Cagliari", 60, 5, "https://bit.ly/2A0wgPw", "qfitzsimon9@twitpic.com", "070145321"));
            hotelDAO.doSave(new HotelBean(UUID.fromString("4213fd86-cf87-41fc-b3b7-ac66b594d086"), "Hotel Girasole", "Via Cassia 17", "Cagliari", 40, 4, "https://bit.ly/2NsZTft", "sburhousea@hubpages.com", "070089734"));

        } catch (SQLException throwables) {
            System.out.println("Errore popolamento Hotel");
            throwables.printStackTrace();
        }

        try {
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("76177fcd-8b7c-46dd-8440-5e1b3dd05111"), "Via Viola 21", "Roma", "La Pagliarella", 10, "https://bit.ly/2Yx6tIf", "017148921", "aterram0@vistaprint.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("0a8ae1b9-2ef3-454d-bbff-543feabc4c00"), "Via Castello 56", "Roma", "Il Paperone", 20, "https://bit.ly/2VhV79c", "013690923", "awerendell1@wiley.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("f57ff5be-e15f-4be0-b863-3482493431e6"), "Via Cattaneo 34", "Firenze", "Made in Italy", 15, "https://bit.ly/3etsFZn", "055112190", "pgaynor2@friendfeed.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("ad261c21-bf13-4b66-9663-c238176953fd"), "Piazza Umberto 85", "Firenze", "Rosso Pomodoro", 15, "https://bit.ly/2YvS7rt", "05548391", "gfouracre3@who.int"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("a452b95f-c660-49eb-aae9-0e575b38c7a9"), "Via Loreto 34", "Angri", "Vecchio Mulino", 20, "https://bit.ly/2Z1utSI", "081890289", "cburk4@about.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("571c73c7-a729-4954-b348-5b70fdc6afb7"), "Via Campi 57", "Angri", "Pizzicotto", 25, "https://bit.ly/2Vje1ML", "081113293", "lalexsandrev5@unisa.it"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("cba7d97b-6efc-4ba1-bef8-3de7388fe60f"), "Via Rota 86", "Bari", "I Due Monti", 30, "https://bit.ly/2CywGNY", "080045770", "ditzkov6@51.la"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("13cf8b92-e658-43bf-bf73-9c645157cea9"), "Via Galvani 96", "Bari", "Donna Sofì", 30, "https://bit.ly/2NsCV8l", "080908991", "mcabotto7@chronoengine.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("777dc01e-e627-4f4e-ad5e-21c31f931798"), "Via Guantai 11", "Milano", "I Patrizio", 35, "https://bit.ly/2NsCXwZ", "021401916", "wchristall8@domainmarket.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("bf434424-b62a-4fba-acc5-51ea796f7775"), "Via Fiorentini 50", "Milano", "La Terrazza", 20, "https://bit.ly/3dxq3by", "020173268", "lfideler9@instagram.com"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("6326f986-40bf-4286-ab7b-cc8710e3b454"), "Via Nasto 16", "Cagliari", "I Rota", 35, "https://bit.ly/2BBu2Xm", "07014561", "nsollimea@blogspot.con"));
            restaurantDAO.doSave(new RestaurantBean(UUID.fromString("4e4e60b1-5a9d-49ab-a0d4-14f04b9001b8"), "Vico Ercolino 8", "Cagliari", "Namì", 35, "https://bit.ly/2CDkL1r", "071139734", "msambeckb@reddit.com"));

        } catch (SQLException throwables) {
            System.out.println("Errore popolamento Ristoranti");
            throwables.printStackTrace();
        }

        try {
            luogoDAO.doSave(new LuogoBean(UUID.fromString("3b2ec08d-d966-4656-a772-64a3fa317566"), "Colosseo", "475 Bluestem Street", "Roma", "Il Colosseo è il più grande anfiteatro del mondo.", "https://bit.ly/3dsLg6w"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("8644fe22-c170-460e-9097-022edd117ca6"), "Pantheon", "055 Fieldstone Crossing", "Roma", "Il Pantheon è un edificio della Roma antica situato nel rione Pigna nel centro storico.", "https://bit.ly/2YwYavZ"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("986ca522-e1d8-4c56-af4c-0f84c83fd266"), "Palazzo Vecchio", "30 Butterfield Alley", "Firenze", "Palazzo Vecchio si trova in piazza della Signoria a Firenze ed è la sede del Comune.", "https://bit.ly/3g2e7jT"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("8ca06888-afb2-4fb0-ba8a-232d44b5bd09"), "Galleria degli Uffizi", "91 Mifflin Parkway", "Firenze", "La Galleria degli Uffizi fa parte del complesso museale fiorentino.", "https://bit.ly/31g1vBt"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("3fddb369-740b-420a-836b-2a6a5536662c"), "Castello Doria", "1162 Muir Trail", "Angri", "Castello di epoca medievale", "https://bit.ly/3i0VkXM"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("d810ef8f-848d-43c8-b4a0-56fce4d64d13"), "Chiesa San Maria", "93 Hallows Plaza", "Angri", "La chiesa sorge sui resti di un antico tempio romando dedicato a Venere.", "https://bit.ly/2VgyLVx"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("4b9d991b-c659-43b8-a6d0-f55b214a858e"), "Basilica San Nicola", "3 Banding Court", "Bari", "La basilica è uno degli esempi più significativi di architettura del romanico pugliese.", "https://bit.ly/3hZRSNh"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("ee6f5f06-2bc9-405e-a288-02524afdf61d"), "Teatro Petruzzelli", "3561 Pond Parkway", "Bari", "Il teatro Petruzzelli è il maggiore teatro di Bari e il quarto più grande d'Italia.", "https://bit.ly/2YyM4CF"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("80b200b6-38ea-4474-9b72-2b89191ab14f"), "Duomo di Milano", "12629 Caliangt Avenue", "Milano", "Il Duomo di Milano è la cattedrale dell'arcidiocesi di Milano.", "https://bit.ly/3i3f9ha"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("1d5da82f-0273-4a92-9fd0-7c008c53a726"), "Castello Sforzesco", "20868 Daystar Place", "Milano", "Il Castello Sforzesco è un grande complesso fortificato eretto nel XV secolo.", "https://bit.ly/3i3x5YX"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("8ab47f3d-e86f-43ec-8636-e06d1ced64d6"), "Bastione di Saint Remy", "05424 Butterfield Point", "Cagliari", "Il bastione di Saint Remy è una delle fortificazioni più importanti di Cagliari.", "https://bit.ly/3hVIn1D"));
            luogoDAO.doSave(new LuogoBean(UUID.fromString("e2e5cc14-8e6f-4a46-b46d-79dd0c93e2b5"), "Torre di San Pancrazio", "47950 Eastlawn Way", "Cagliari", "La torre più alta di Cagliari, simbolo della città.", "https://bit.ly/2ZcTQRG"));

        } catch (SQLException throwables) {
            System.out.println("Errore popolamento Luoghi");
            throwables.printStackTrace();
        }

        try {
            tourDAO.doSave(new TourBean(UUID.fromString("da254c9a-80cf-48f4-8365-83690b81e3e6"), UUID.fromString("3b2ec08d-d966-4656-a772-64a3fa317566"), 10.0, 10));
            tourDAO.doSave(new TourBean(UUID.fromString("0566ddfd-c440-4481-9379-4a4fe3da7f4e"), UUID.fromString("8644fe22-c170-460e-9097-022edd117ca6"), 15.5, 3));
            tourDAO.doSave(new TourBean(UUID.fromString("2668d026-5a2d-49fb-9fa8-e97146f89083"), UUID.fromString("986ca522-e1d8-4c56-af4c-0f84c83fd266"), 5.0, 5));
            tourDAO.doSave(new TourBean(UUID.fromString("df966026-4bbe-46c5-98cd-58822d0f42c3"), UUID.fromString("8ca06888-afb2-4fb0-ba8a-232d44b5bd09"), 5.0, 9));
            tourDAO.doSave(new TourBean(UUID.fromString("587d689d-064f-497b-b856-1623dc2675e8"), UUID.fromString("3fddb369-740b-420a-836b-2a6a5536662c"), 20.0, 11));
            tourDAO.doSave(new TourBean(UUID.fromString("e28f809b-1344-4d2c-bb6a-b4fd48513c17"), UUID.fromString("d810ef8f-848d-43c8-b4a0-56fce4d64d13"), 18.0, 15));
            tourDAO.doSave(new TourBean(UUID.fromString("22151f05-890f-46ba-8846-195837716078"), UUID.fromString("4b9d991b-c659-43b8-a6d0-f55b214a858e"), 16.0, 8));
            tourDAO.doSave(new TourBean(UUID.fromString("3c58ddee-fb8e-41bc-887b-643acf65ea50"), UUID.fromString("ee6f5f06-2bc9-405e-a288-02524afdf61d"), 12.0, 19));
            tourDAO.doSave(new TourBean(UUID.fromString("8997b08c-c56d-43e0-8b65-c18f4a4cdbcb"), UUID.fromString("80b200b6-38ea-4474-9b72-2b89191ab14f"), 14.0, 4));
            tourDAO.doSave(new TourBean(UUID.fromString("34329d18-8b00-4561-8a9b-933e66184055"), UUID.fromString("1d5da82f-0273-4a92-9fd0-7c008c53a726"), 22.0, 21));
            tourDAO.doSave(new TourBean(UUID.fromString("8781b804-a29d-466a-81d9-0bdb814e79c9"), UUID.fromString("8ab47f3d-e86f-43ec-8636-e06d1ced64d6"), 10.0, 20));
            tourDAO.doSave(new TourBean(UUID.fromString("05658f70-3e00-4a4c-b1a8-1d3f7272d867"), UUID.fromString("e2e5cc14-8e6f-4a46-b46d-79dd0c93e2b5"), 10.0, 7));

        } catch (SQLException throwables) {
            System.out.println("Errore popolamento Tour");
            throwables.printStackTrace();
        }

        try {
            pacchettoDAO.doSave(new PacchettoBean(UUID.fromString("529c170d-20f4-4d30-afa7-e552dd0dd3b9"), 7*4*hotelDAO.retrieveByKey(UUID.fromString("39867a1b-eb84-4fe6-9c80-4d973c6fa3b7")).getCostoNotte(), "youpon@gmail.com", UUID.fromString("39867a1b-eb84-4fe6-9c80-4d973c6fa3b7"), 7, true, 4));
            pacchettoDAO.doSave(new PacchettoBean(UUID.fromString("3cdce9cf-1f1d-4188-8dce-1bcaad037571"), 3*3*hotelDAO.retrieveByKey(UUID.fromString("cd21f109-8ea6-4f70-94e4-3b4668ab7cb7")).getCostoNotte(), "youpon@gmail.com", UUID.fromString("cd21f109-8ea6-4f70-94e4-3b4668ab7cb7"), 3, true, 3));
            pacchettoDAO.doSave(new PacchettoBean(UUID.fromString("9249e484-78dd-471a-9df1-303e03f9ac8e"), 5*2*hotelDAO.retrieveByKey(UUID.fromString("dd19d5e0-920a-4a03-bc30-f15db9f1756c")).getCostoNotte(), "youpon@gmail.com", UUID.fromString("dd19d5e0-920a-4a03-bc30-f15db9f1756c"), 5, true, 2));
            pacchettoDAO.doSave(new PacchettoBean(UUID.fromString("99d7acc6-de11-48c2-a4b3-03d03f5a2673"), 7*2*hotelDAO.retrieveByKey(UUID.fromString("798c621c-e695-4fe2-90a5-08f09ceebd33")).getCostoNotte(), "youpon@gmail.com", UUID.fromString("798c621c-e695-4fe2-90a5-08f09ceebd33"), 7, true, 2));
            pacchettoDAO.doSave(new PacchettoBean(UUID.fromString("d1cf37d7-d4d0-4902-a567-a60fdaaf1e4d"), 5*4*hotelDAO.retrieveByKey(UUID.fromString("3b010399-bd87-4560-bc6f-505edf38b2a2")).getCostoNotte(), "youpon@gmail.com", UUID.fromString("3b010399-bd87-4560-bc6f-505edf38b2a2"), 5, true, 4));
            pacchettoDAO.doSave(new PacchettoBean(UUID.fromString("d34407cf-12a7-478e-a638-42ab64b98db2"), 3*5*hotelDAO.retrieveByKey(UUID.fromString("5f37f3bf-84ba-45ab-a5dd-bb0ded21230c")).getCostoNotte(), "youpon@gmail.com", UUID.fromString("5f37f3bf-84ba-45ab-a5dd-bb0ded21230c"), 3, true, 5));

        }

        catch (SQLException throwables) {
            System.out.println("Errore popolamento Pacchetti");
            throwables.printStackTrace();
        }
    }
}
