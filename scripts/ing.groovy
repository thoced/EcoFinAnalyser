import transaction.TransactionModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/* ***
axa import des données provenant de l'organisme bancaire - ING
Auteur: THONON Cedric

IN: FILE descriptor du fichier à importer
OUT : List de TransactionModel
 */

def getNameOrganisme(){
    return "ING";
}

def getOrganismeComment(){
    return "ING organisme bancaire";
}


def parse(File file) {

     def line;
     def List<TransactionModel> listTransactions = new ArrayList<TransactionModel>();

     file.withReader {reader ->
         line = reader.readLine();
         line = reader.readLine();
         while(line != null){
             TransactionModel model = new TransactionModel();
             lines = line.split(";");
             // reception du numero de compte
             model.setCompte(lines[1].trim());
             // reception du localdatetime
             model.setLocalDateTime(convertDate(lines[22].trim()));
             // reception de l'amount
             model.setAmount(lines[7].trim().toDouble());
             // reception compte contrepartie
             model.setCompteContrepartie(line[10].trim());
             // reception comment
             model.setComment(lines[9].trim());
             // reception du type de transaction
             model.setType(convertType(lines[6].trim()));

            // ajout dans le conteneur
             listTransactions.add(model);
            // passage à la ligne suivante
             line = reader.readLine();
         }
     }

    return listTransactions;

 }

def private convertDate(def dateText){
    def DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    def dateTime = dateText.split("\\.");
    LocalDateTime localDate = LocalDateTime.parse(dateTime[0],dateTimeFormatter);
    return localDate;
}

def private convertType(String typeText){

    if(typeText.contains("Frais carte bancaire AXA"))
        return TransactionModel.TYPE_TRANSACTION.FRAIS;

    if(typeText.contains("Virement"))
        return TransactionModel.TYPE_TRANSACTION.VIREMENT;

    if(typeText.contains("Achat par carte bancaire AXA"))
        return TransactionModel.TYPE_TRANSACTION.ACHAT;

    if(typeText.contains("Retrait"))
        return TransactionModel.TYPE_TRANSACTION.RETRAIT;

    return TransactionModel.TYPE_TRANSACTION.NONE;
}

