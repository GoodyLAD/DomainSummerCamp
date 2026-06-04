public class DNASequencer {

    // Initial capacity pehle hi de di taki baar-baar resize na ho
    private StringBuilder dnaSequence;

    public DNASequencer(int expectedLength) {
        dnaSequence = new StringBuilder(expectedLength);
    }

    // Sensor se aaye characters efficiently append karne ke liye
    public void ingestSequence(char[] sensorData) {

        for (char nucleotide : sensorData) {

            // += ya concat use nahi karna
            dnaSequence.append(nucleotide);
        }
    }

    // DNA sequence me first occurrence ko replace karna
    public void mutateDNA(String target, String replacement) {

        int startIndex = dnaSequence.indexOf(target);

        // Agar target mila to wahi par replace kar do
        if (startIndex != -1) {

            dnaSequence.replace(
                    startIndex,
                    startIndex + target.length(),
                    replacement
            );
        }
    }

    // Current DNA sequence return karne ke liye
    public String getSequence() {
        return dnaSequence.toString();
    }

    public static void main(String[] args) {

        // Large DNA sequence ke liye capacity reserve kar di
        DNASequencer alienDNA = new DNASequencer(100000);

        // Sensor data
        char[] sensorData = {
                'A', 'C', 'T', 'G',
                'G', 'A', 'C', 'T',
                'A', 'A', 'G', 'T'
        };

        // DNA ingest kiya
        alienDNA.ingestSequence(sensorData);

        System.out.println("Original DNA:");
        System.out.println(alienDNA.getSequence());

        // Mutation perform ki
        alienDNA.mutateDNA("GGAC", "TTTT");

        System.out.println("\nAfter Mutation:");
        System.out.println(alienDNA.getSequence());
    }
}